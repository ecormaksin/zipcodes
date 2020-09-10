#!/bin/sh

cd `dirname $0`

export VAULT_TOKEN=`cat ./vault/vault_passwd`

java -jar ./zipcodes.jar --spring.profiles.active=production
