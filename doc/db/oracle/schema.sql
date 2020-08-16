-- Project Name : 郵便番号検索システム
-- Date/Time    : 2020/08/16 14:44:46
-- Author       : eight
-- RDBMS Type   : Oracle Database
-- Application  : A5:SQL Mk-2

-- 郵便番号
create table ZIP_CODES (
  ID NUMBER(11,0) not null
  , JAPANESE_LOCAL_GOVERMENT_CODE VARCHAR2(5)
  , OLD_ZIP_CODE VARCHAR2(5)
  , ZIP_CODE VARCHAR2(7)
  , PREFECTURE_NAME_KANA VARCHAR2(10 CHAR)
  , CITY_NAME_KANA VARCHAR2(30 CHAR)
  , TOWN_NAME_KANA VARCHAR2(100 CHAR)
  , PREFECTURE_NAME VARCHAR2(5 CHAR)
  , CITY_NAME VARCHAR2(20 CHAR)
  , TOWN_NAME VARCHAR2(50 CHAR)
  , TOWN_DEVIDED_FLAG VARCHAR2(1)
  , ISSUED_PER_KOAZA_FLAG VARCHAR2(1)
  , CHOME_TOWN_FLAG VARCHAR2(1)
  , HAS_MULTIPLE_TOWN_FLAG VARCHAR2(1)
  , UPDATE_DISPLAY_FLAG VARCHAR2(1)
  , CHANGE_REASON_FLAG VARCHAR2(1)
  , constraint ZIP_CODES_PKC primary key (ID)
) ;

alter table ZIP_CODES add constraint ZIP_CODES_IX1
  unique (JAPANESE_LOCAL_GOVERMENT_CODE,OLD_ZIP_CODE,ZIP_CODE,PREFECTURE_NAME_KANA,CITY_NAME_KANA,TOWN_NAME_KANA,PREFECTURE_NAME,CITY_NAME,TOWN_NAME,TOWN_DEVIDED_FLAG,ISSUED_PER_KOAZA_FLAG,CHOME_TOWN_FLAG,HAS_MULTIPLE_TOWN_FLAG,UPDATE_DISPLAY_FLAG,CHANGE_REASON_FLAG) ;

create index ZIP_CODES_IX2
  on ZIP_CODES(JAPANESE_LOCAL_GOVERMENT_CODE);

create index ZIP_CODES_IX3
  on ZIP_CODES(ZIP_CODE);

create index ZIP_CODES_IX4
  on ZIP_CODES(PREFECTURE_NAME);

create index ZIP_CODES_IX5
  on ZIP_CODES(CITY_NAME);

create index ZIP_CODES_IX6
  on ZIP_CODES(PREFECTURE_NAME_KANA);

create index ZIP_CODES_IX7
  on ZIP_CODES(CITY_NAME_KANA);

-- 郵便番号ワーク
create table ZIP_CODES_WORK (
  JAPANESE_LOCAL_GOVERMENT_CODE VARCHAR2(5)
  , OLD_ZIP_CODE VARCHAR2(5)
  , ZIP_CODE VARCHAR2(7)
  , PREFECTURE_NAME_KANA VARCHAR2(10 CHAR)
  , CITY_NAME_KANA VARCHAR2(30 CHAR)
  , TOWN_NAME_KANA VARCHAR2(100 CHAR)
  , PREFECTURE_NAME VARCHAR2(5 CHAR)
  , CITY_NAME VARCHAR2(20 CHAR)
  , TOWN_NAME VARCHAR2(50 CHAR)
  , TOWN_DEVIDED_FLAG VARCHAR2(1)
  , ISSUED_PER_KOAZA_FLAG VARCHAR2(1)
  , CHOME_TOWN_FLAG VARCHAR2(1)
  , HAS_MULTIPLE_TOWN_FLAG VARCHAR2(1)
  , UPDATE_DISPLAY_FLAG VARCHAR2(1)
  , CHANGE_REASON_FLAG VARCHAR2(1)
) ;

-- 住所
create view ADDRESSES as 
SELECT
    ROW_NUMBER() OVER ( 
        ORDER BY
            ZIP_CODE
            , PREFECTURE_NAME
            , CITY_NAME
            , TOWN_NAME
    ) AS ID
    , ZIP_CODE                                  -- 郵便番号
    , PREFECTURE_NAME                           -- 都道府県名
    , CITY_NAME                                 -- 市区町村名
    , TOWN_NAME                                 -- 町域名
    , PREFECTURE_NAME_KANA                      -- 都道府県名ｶﾅ
    , CITY_NAME_KANA                            -- 市区町村名ｶﾅ
    , TOWN_NAME_KANA                            -- 町域名ｶﾅ
FROM
    ( 
        SELECT
            ZIP_CODE
            , PREFECTURE_NAME
            , CITY_NAME
            , TOWN_NAME
            , PREFECTURE_NAME_KANA
            , CITY_NAME_KANA
            , TOWN_NAME_KANA 
        FROM
            ( 
                SELECT
                    ZIP_CODE
                    , PREFECTURE_NAME
                    , CITY_NAME
                    , CASE 
                        WHEN NVL(TOWN_NAME, '') = '以下に掲載がない場合' 
                            THEN '' 
                        ELSE TOWN_NAME 
                        END AS TOWN_NAME
                    , PREFECTURE_NAME_KANA
                    , CITY_NAME_KANA
                    , CASE 
                        WHEN NVL(TOWN_NAME_KANA, '') = 'ｲｶﾆｹｲｻｲｶﾞﾅｲﾊﾞｱｲ' 
                            THEN '' 
                        ELSE TOWN_NAME_KANA 
                        END AS TOWN_NAME_KANA 
                FROM
                    ( 
                        SELECT
                            ZIP_CODE
                            , PREFECTURE_NAME
                            , CITY_NAME
                            , CASE 
                                WHEN INSTR(TOWN_NAME, '（') > 0 
                                AND ( 
                                    INSTR(TOWN_NAME, '）') = 0 
                                    OR INSTR(TOWN_NAME, '（') < INSTR(TOWN_NAME, '）')
                                ) 
                                    THEN SUBSTR(TOWN_NAME, 1, INSTR(TOWN_NAME, '（') - 1) 
                                WHEN INSTR(TOWN_NAME, '）') > 0 
                                OR INSTR(TOWN_NAME, '、') > 0 
                                    THEN NULL 
                                ELSE TOWN_NAME 
                                END AS TOWN_NAME
                            , PREFECTURE_NAME_KANA
                            , CITY_NAME_KANA
                            , CASE 
                                WHEN INSTR(TOWN_NAME_KANA, '(') > 0 
                                AND ( 
                                    INSTR(TOWN_NAME_KANA, ')') = 0 
                                    OR INSTR(TOWN_NAME_KANA, '(') < INSTR(TOWN_NAME_KANA, ')')
                                ) 
                                    THEN SUBSTR( 
                                    TOWN_NAME_KANA
                                    , 1
                                    , INSTR(TOWN_NAME_KANA, '(') - 1
                                ) 
                                WHEN INSTR(TOWN_NAME_KANA, ')') > 0 
                                OR INSTR(TOWN_NAME_KANA, '、') > 0 
                                    THEN NULL 
                                ELSE TOWN_NAME_KANA 
                                END AS TOWN_NAME_KANA 
                        FROM
                            ZIP_CODES 
                        WHERE
                            UPDATE_DISPLAY_FLAG IN ('0', '1')
                    ) SQ_A 
                WHERE
                    TOWN_NAME IS NOT NULL
            ) SQ_B 
        GROUP BY
            ZIP_CODE
            , PREFECTURE_NAME
            , CITY_NAME
            , TOWN_NAME
            , PREFECTURE_NAME_KANA
            , CITY_NAME_KANA
            , TOWN_NAME_KANA
    ) SQ_C

;

-- 市区町村
create view CITIES as 
SELECT DISTINCT
  SUBSTR(JAPANESE_LOCAL_GOVERMENT_CODE, 1, 2) AS PREFECTURE_CODE -- 都道府県コード
  , JAPANESE_LOCAL_GOVERMENT_CODE -- 地方公共団体コード
  , PREFECTURE_NAME -- 都道府県名
  , CITY_NAME -- 市区町村名
FROM
  ZIP_CODES
WHERE
  UPDATE_DISPLAY_FLAG IN ('0', '1')
;

-- 都道府県
create view PREFECTURES as 
SELECT DISTINCT
  SUBSTR(JAPANESE_LOCAL_GOVERMENT_CODE, 1, 2) AS PREFECTURE_CODE -- 都道府県コード
  , PREFECTURE_NAME -- 都道府県名
FROM
  ZIP_CODES
WHERE
  UPDATE_DISPLAY_FLAG IN ('0', '1')
;

comment on table ADDRESSES is '住所';
comment on column ADDRESSES.ID is 'ID';
comment on column ADDRESSES.ZIP_CODE is '郵便番号';
comment on column ADDRESSES.PREFECTURE_NAME is '都道府県名';
comment on column ADDRESSES.CITY_NAME is '市区町村名';
comment on column ADDRESSES.TOWN_NAME is '町域名';
comment on column ADDRESSES.PREFECTURE_NAME_KANA is '都道府県名ｶﾅ';
comment on column ADDRESSES.CITY_NAME_KANA is '市区町村名ｶﾅ';
comment on column ADDRESSES.TOWN_NAME_KANA is '町域名ｶﾅ';

comment on table CITIES is '市区町村';
comment on column CITIES.PREFECTURE_CODE is '都道府県コード';
comment on column CITIES.JAPANESE_LOCAL_GOVERMENT_CODE is '地方公共団体コード';
comment on column CITIES.PREFECTURE_NAME is '都道府県名';
comment on column CITIES.CITY_NAME is '市区町村名';

comment on table PREFECTURES is '都道府県';
comment on column PREFECTURES.PREFECTURE_CODE is '都道府県コード';
comment on column PREFECTURES.PREFECTURE_NAME is '都道府県名';

comment on table ZIP_CODES is '郵便番号	 「ZIP_CODES_WORK」テーブルからID（連番）を付与してシステム上正式に参照するテーブル';
comment on column ZIP_CODES.ID is 'ID';
comment on column ZIP_CODES.JAPANESE_LOCAL_GOVERMENT_CODE is '全国地方公共団体コード	 JIS X0401、X0402';
comment on column ZIP_CODES.OLD_ZIP_CODE is '旧郵便番号';
comment on column ZIP_CODES.ZIP_CODE is '郵便番号';
comment on column ZIP_CODES.PREFECTURE_NAME_KANA is '都道府県名カナ	 半角カタカナ（コード順に掲載）';
comment on column ZIP_CODES.CITY_NAME_KANA is '市区町村名カナ	 半角カタカナ（コード順に掲載）';
comment on column ZIP_CODES.TOWN_NAME_KANA is '町域名カナ	 半角カタカナ（五十音順に掲載）';
comment on column ZIP_CODES.PREFECTURE_NAME is '都道府県名	 漢字（コード順に掲載）';
comment on column ZIP_CODES.CITY_NAME is '市区町村名	 漢字（コード順に掲載）';
comment on column ZIP_CODES.TOWN_NAME is '町域名	 漢字（五十音順に掲載）';
comment on column ZIP_CODES.TOWN_DEVIDED_FLAG is '複数番号付与町域フラグ	 一町域が二以上の郵便番号で表される場合の表示（「1」は該当、「0」は該当せず）';
comment on column ZIP_CODES.ISSUED_PER_KOAZA_FLAG is '小字毎番号付与フラグ	 小字毎に番地が起番されている町域の表示（「1」は該当、「0」は該当せず）';
comment on column ZIP_CODES.CHOME_TOWN_FLAG is '丁目保有町域フラグ	 丁目を有する町域の場合の表示（「1」は該当、「0」は該当せず）';
comment on column ZIP_CODES.HAS_MULTIPLE_TOWN_FLAG is '複数町域保有フラグ	 一つの郵便番号で二以上の町域を表す場合の表示（「1」は該当、「0」は該当せず）';
comment on column ZIP_CODES.UPDATE_DISPLAY_FLAG is '更新の表示	 「0」は変更なし、「1」は変更あり、「2」廃止（廃止データのみ使用）';
comment on column ZIP_CODES.CHANGE_REASON_FLAG is '変更理由	 「0」は変更なし、「1」市政・区政・町政・分区・政令指定都市施行、「2」住居表示の実施、「3」区画整理、「4」郵便区調整等、「5」訂正、「6」廃止（廃止データのみ使用）';

comment on table ZIP_CODES_WORK is '郵便番号ワーク	 郵便局が提供している郵便番号データ
https://www.post.japanpost.jp/zipcode/dl/readme.html
csvファイルをインポートするためのワークテーブル';
comment on column ZIP_CODES_WORK.JAPANESE_LOCAL_GOVERMENT_CODE is '全国地方公共団体コード	 JIS X0401、X0402';
comment on column ZIP_CODES_WORK.OLD_ZIP_CODE is '旧郵便番号';
comment on column ZIP_CODES_WORK.ZIP_CODE is '郵便番号';
comment on column ZIP_CODES_WORK.PREFECTURE_NAME_KANA is '都道府県名カナ	 半角カタカナ（コード順に掲載）';
comment on column ZIP_CODES_WORK.CITY_NAME_KANA is '市区町村名カナ	 半角カタカナ（コード順に掲載）';
comment on column ZIP_CODES_WORK.TOWN_NAME_KANA is '町域名カナ	 半角カタカナ（五十音順に掲載）';
comment on column ZIP_CODES_WORK.PREFECTURE_NAME is '都道府県名	 漢字（コード順に掲載）';
comment on column ZIP_CODES_WORK.CITY_NAME is '市区町村名	 漢字（コード順に掲載）';
comment on column ZIP_CODES_WORK.TOWN_NAME is '町域名	 漢字（五十音順に掲載）';
comment on column ZIP_CODES_WORK.TOWN_DEVIDED_FLAG is '複数番号付与町域フラグ	 一町域が二以上の郵便番号で表される場合の表示（「1」は該当、「0」は該当せず）';
comment on column ZIP_CODES_WORK.ISSUED_PER_KOAZA_FLAG is '小字毎番号付与フラグ	 小字毎に番地が起番されている町域の表示（「1」は該当、「0」は該当せず）';
comment on column ZIP_CODES_WORK.CHOME_TOWN_FLAG is '丁目保有町域フラグ	 丁目を有する町域の場合の表示（「1」は該当、「0」は該当せず）';
comment on column ZIP_CODES_WORK.HAS_MULTIPLE_TOWN_FLAG is '複数町域保有フラグ	 一つの郵便番号で二以上の町域を表す場合の表示（「1」は該当、「0」は該当せず）';
comment on column ZIP_CODES_WORK.UPDATE_DISPLAY_FLAG is '更新の表示	 「0」は変更なし、「1」は変更あり、「2」廃止（廃止データのみ使用）';
comment on column ZIP_CODES_WORK.CHANGE_REASON_FLAG is '変更理由	 「0」は変更なし、「1」市政・区政・町政・分区・政令指定都市施行、「2」住居表示の実施、「3」区画整理、「4」郵便区調整等、「5」訂正、「6」廃止（廃止データのみ使用）';

