#!/bin/sh

cd `dirname $0`

VAULT_PASSWORD_FILE_NAME=vault_passwd
export VAULT_TOKEN=`cat "./vault/${VAULT_PASSWORD_FILE_NAME}"`

sh ./vault/start_vault.sh 0 "${VAULT_PASSWORD_FILE_NAME}"

java -jar ./zipcodes.jar --spring.profiles.active=production
