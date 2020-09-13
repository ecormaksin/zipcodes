#!/bin/sh

if [ $# -ne 2 ]; then
	echo "引数が正しくありません。"
	exit 0
fi

cd `dirname $0`

USE_DOCKER_CONTAINER="$1"
VAULT_DEV_ROOT_TOKEN_ID=`cat "$2"`

if [ "${USE_DOCKER_CONTAINER}" = "1" ]; then

	DOCKER_CONTAINER_NAME=local_vault

	DOCKER_CONTAINER_RESULT=`docker ps -a | grep "${DOCKER_CONTAINER_NAME}"`
	if [ -n "${DOCKER_CONTAINER_RESULT}" ]; then
		exit 0
	fi

	docker run --cap-add=IPC_LOCK -d -e "VAULT_DEV_ROOT_TOKEN_ID=${VAULT_DEV_ROOT_TOKEN_ID}" --name="${DOCKER_CONTAINER_NAME}" -p 8200:8200 --rm vault

else

	PS_RESULT=`ps aux | grep "vault server" | grep -v grep`
	if [ -n "${PS_RESULT}" ]; then
		exit 0
	fi

	vault server -dev -dev-root-token-id="${VAULT_DEV_ROOT_TOKEN_ID}" &

fi

export VAULT_ADDR='http://127.0.0.1:8200'
export VAULT_TOKEN="${VAULT_DEV_ROOT_TOKEN_ID}"

while :
	do
		VAULT_STATUS_RESULT=`vault status 2>/dev/null | grep "Initialized     true"`
		if [ -n "${VAULT_STATUS_RESULT}" ] ; then
			break
		fi
	done

vault login "${VAULT_DEV_ROOT_TOKEN_ID}"

vault kv put secret/zipcodes @secrets.json
