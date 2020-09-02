# DB関連メモ
```
docker run -d --hostname local-oracle-db --name local-oracle-db -p 49161:1521 -p 58080:8080 -e ORACLE_ALLOW_REMOTE=true -e ORACLE_DISABLE_ASYNCH_IO=true wnameless/oracle-xe-11g-r2

docker exec -d local-oracle-db bash -c "mkdir -p /opt/zip_codes/db"
docker exec -d local-oracle-db bash -c "chown -R oracle:dba /opt/zip_codes/db"
docker exec -d local-oracle-db bash -c "mkdir -p /opt/zip_codes/db/import"
docker exec -d local-oracle-db bash -c "chown -R oracle:dba /opt/zip_codes/db/import"

CREATE TABLESPACE ZIP_CODES
	DATAFILE '/opt/zip_codes/db/data.dbf' SIZE 100M
	AUTOEXTEND ON NEXT 500K MAXSIZE 15G;

CREATE ROLE DB_LOCAL_ADMIN;

GRANT CONNECT
    , CREATE ANY INDEX          , ALTER ANY INDEX               , DROP ANY INDEX
    , CREATE MATERIALIZED VIEW  , ALTER ANY MATERIALIZED VIEW   , DROP ANY MATERIALIZED VIEW
    , CREATE ANY PROCEDURE      , ALTER ANY PROCEDURE           , DROP ANY PROCEDURE         , EXECUTE ANY PROCEDURE
    , CREATE ANY SEQUENCE       , ALTER ANY SEQUENCE            , DROP ANY SEQUENCE
    , CREATE ANY TABLE          , ALTER ANY TABLE               , DROP ANY TABLE
    , CREATE ANY VIEW           , DROP ANY VIEW
    , CREATE PROCEDURE
    , CREATE SEQUENCE
    , CREATE TABLE
    , CREATE VIEW
    , INSERT ANY TABLE    , SELECT ANY TABLE    , UPDATE ANY TABLE    , DELETE ANY TABLE    , LOCK ANY TABLE
    , SELECT ANY SEQUENCE
    TO DB_LOCAL_ADMIN
;

CREATE USER ZIP_CODE
	IDENTIFIED BY "Password1234"
	DEFAULT TABLESPACE ZIP_CODES
	TEMPORARY TABLESPACE TEMP;

GRANT DB_LOCAL_ADMIN TO ZIP_CODE;

ALTER USER ZIP_CODE QUOTA UNLIMITED ON ZIP_CODES;

-- シーケンスの作成
EXEC RESET_SEQUENCE('ZIP_CODES', 'ID', 'ZIP_CODES_SEQ');

--CREATE SEQUENCE ZIP_CODES_SEQ;

/*
-- 表領域の一覧を取得する
SELECT * FROM DBA_TABLESPACES;
-- データファイルの一覧を取得する
SELECT * FROM DBA_DATA_FILES;

-- （★リセット用）ユーザーの削除
DROP USER ZIP_CODE CASCADE;

-- （★リセット用）表領域の削除
DROP TABLESPACE ZIP_CODES
INCLUDING CONTENTS
AND DATAFILES
CASCADE CONSTRAINTS;
*/
```

## 郵便番号データのインポート

### シーケンスリセット用のストアドプロシージャを作成する

```
cd ./doc/db/oracle
docker cp ./create_or_replace_procedure_reset_sequence.sql local-oracle-db:opt/zip_codes/db

docker exec -it local-oracle-db bash

# コンテナのbash上での実行コマンド
cd /opt/zip_codes/db
sqlplus ZIP_CODE/Password1234

# SQL*Plus内での実行コマンド
@create_or_replace_procedure_reset_sequence.sql
# Enter後のプロンプトで"/"(Enter)で処理を終了する。

```

### 郵便番号データをワークテーブルへインポートし、本テーブルへコピーする

```
cd ./doc/db/oracle
docker cp ./import/. local-oracle-db:opt/zip_codes/db/import

docker exec -it local-oracle-db bash

# コンテナのbash上での実行コマンド
sh /opt/zip_codes/db/import/import.sh
```

## Oracle Cloud Autonomous Database上でのユーザー作成

https://docs.oracle.com/en/cloud/paas/atp-cloud/atpug/manage-users-create.html#GUID-54CA837B-CD6A-4ED2-A960-5874535818CB

```
CREATE ROLE DB_LOCAL_ADMIN;

GRANT CONNECT
    , CREATE ANY INDEX          , ALTER ANY INDEX               , DROP ANY INDEX
    , CREATE MATERIALIZED VIEW  , ALTER ANY MATERIALIZED VIEW   , DROP ANY MATERIALIZED VIEW
    , CREATE ANY PROCEDURE      , ALTER ANY PROCEDURE           , DROP ANY PROCEDURE         , EXECUTE ANY PROCEDURE
    , CREATE ANY SEQUENCE       , ALTER ANY SEQUENCE            , DROP ANY SEQUENCE
    , CREATE ANY TABLE          , ALTER ANY TABLE               , DROP ANY TABLE
    , CREATE ANY VIEW           , DROP ANY VIEW
    , CREATE PROCEDURE
    , CREATE SEQUENCE
    , CREATE TABLE
    , CREATE VIEW
    , INSERT ANY TABLE    , SELECT ANY TABLE    , UPDATE ANY TABLE    , DELETE ANY TABLE    , LOCK ANY TABLE
    , SELECT ANY SEQUENCE
    TO DB_LOCAL_ADMIN
;

CREATE USER ZIP_CODE IDENTIFIED BY <任意のパスワード>;
GRANT CREATE SESSION TO ZIP_CODE;

GRANT DB_LOCAL_ADMIN TO ZIP_CODE;
GRANT UNLIMITED TABLESPACE TO ZIP_CODE;
```

# Hashicorp Vault

## Docker

https://hub.docker.com/_/vault

Windowsの場合はGit Bashで実行しないとエラーになった。
また、Docker Hubのページの `Running Vault in Server Mode` に記載されているコマンドでは、ホストからアクセス可能なコンテナを起動できなかった。

解決したのは以下のURLのやりとり

https://github.com/hashicorp/vault/issues/441#issuecomment-123362274

### Docker Hub ページに掲載されているコマンド

```
docker run --cap-add=IPC_LOCK -e 'VAULT_LOCAL_CONFIG={"backend": {"file": {"path": "/vault/file"}}, "default_lease_ttl": "168h", "max_lease_ttl": "720h"}' vault server
```

### ホストからアクセス可能になったコマンド

- `api_addr` の設定が必要
- TLSを無効
- tcpのアドレスを `0.0.0.0:8200` へ変更

```
docker run --cap-add=IPC_LOCK -e 'VAULT_LOCAL_CONFIG={"backend": {"file": {"path": "/vault/file"}}, "api_addr": "http://127.0.0.1:8200", "listener": {"tcp": {"address": "0.0.0.0:8200", "tls_disable": "true"}}}}' -d --name=local-vault -p 8200:8200 vault server
```

```
export VAULT_ADDR='http://127.0.0.1:8200'
vault operator init
```

出力された `Unseal Key 1-5` と `Initial Root Token` を控えておく。

```shell
vault secrets enable -path=secret kv

cd ./vault
vault kv put secret/zipcodes @db.json

vault policy write zipcodes-read-policy zipcodes-read-policy.hcl

vault token create -policy=zipcodes-read-policy

```
