INSERT INTO ZIP_CODES (
  ID
  , JAPANESE_LOCAL_GOVERMENT_CODE
  , OLD_ZIP_CODE
  , ZIP_CODE
  , PREFECTURE_NAME_KANA
  , CITY_NAME_KANA
  , TOWN_NAME_KANA
  , PREFECTURE_NAME
  , CITY_NAME
  , TOWN_NAME
  , TOWN_DEVIDED_FLAG
  , ISSUED_PER_KOAZA_FLAG
  , CHOME_TOWN_FLAG
  , HAS_MULTIPLE_TOWN_FLAG
  , UPDATE_DISPLAY_FLAG
  , CHANGE_REASON_FLAG
) SELECT
  ZIP_CODES_SEQ.nextval
  , JAPANESE_LOCAL_GOVERMENT_CODE
  , OLD_ZIP_CODE
  , ZIP_CODE
  , PREFECTURE_NAME_KANA
  , CITY_NAME_KANA
  , TOWN_NAME_KANA
  , PREFECTURE_NAME
  , CITY_NAME
  , TOWN_NAME
  , TOWN_DEVIDED_FLAG
  , ISSUED_PER_KOAZA_FLAG
  , CHOME_TOWN_FLAG
  , HAS_MULTIPLE_TOWN_FLAG
  , UPDATE_DISPLAY_FLAG
  , CHANGE_REASON_FLAG
FROM
  ZIP_CODES_WORK
WHERE
  UPDATE_DISPLAY_FLAG IN ('0', '1')
;

EXIT
