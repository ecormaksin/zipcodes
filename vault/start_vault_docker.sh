#!/bin/sh

if [ $# -ne 1 ]; then
	echo "引数が正しくありません。"
	exit 0
fi

cd `dirname $0`

DOCKER_CONTAINER_NAME=local_vault
VAULT_DEV_ROOT_TOKEN_ID=`cat "$1"`

DOCKER_CONTAINER_RESULT=`docker ps -a | grep "${DOCKER_CONTAINER_NAME}"`
if [ -n "${DOCKER_CONTAINER_RESULT}" ]; then
	exit 0
fi

docker run --cap-add=IPC_LOCK -d -e "VAULT_DEV_ROOT_TOKEN_ID=${VAULT_DEV_ROOT_TOKEN_ID}" --name="${DOCKER_CONTAINER_NAME}" -p 8200:8200 --rm vault

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

#vault secrets enable -path=secret kv

vault kv put secret/zipcodes @secrets.json
