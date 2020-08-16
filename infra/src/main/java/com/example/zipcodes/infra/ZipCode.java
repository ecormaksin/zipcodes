package com.example.zipcodes.infra;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ZIP_CODES database table.
 * 
 */
@Entity
@Table(name="ZIP_CODES")
@NamedQuery(name="ZipCode.findAll", query="SELECT z FROM ZipCode z")
public class ZipCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ZIP_CODES_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ZIP_CODES_ID_GENERATOR")
	private long id;

	@Column(name="CHANGE_REASON_FLAG")
	private String changeReasonFlag;

	@Column(name="CHOME_TOWN_FLAG")
	private String chomeTownFlag;

	@Column(name="CITY_NAME")
	private String cityName;

	@Column(name="CITY_NAME_KANA")
	private String cityNameKana;

	@Column(name="HAS_MULTIPLE_TOWN_FLAG")
	private String hasMultipleTownFlag;

	@Column(name="ISSUED_PER_KOAZA_FLAG")
	private String issuedPerKoazaFlag;

	@Column(name="JAPANESE_LOCAL_GOVERMENT_CODE")
	private String japaneseLocalGovermentCode;

	@Column(name="OLD_ZIP_CODE")
	private String oldZipCode;

	@Column(name="PREFECTURE_NAME")
	private String prefectureName;

	@Column(name="PREFECTURE_NAME_KANA")
	private String prefectureNameKana;

	@Column(name="TOWN_DEVIDED_FLAG")
	private String townDevidedFlag;

	@Column(name="TOWN_NAME")
	private String townName;

	@Column(name="TOWN_NAME_KANA")
	private String townNameKana;

	@Column(name="UPDATE_DISPLAY_FLAG")
	private String updateDisplayFlag;

	@Column(name="ZIP_CODE")
	private String zipCode;

	public ZipCode() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getChangeReasonFlag() {
		return this.changeReasonFlag;
	}

	public void setChangeReasonFlag(String changeReasonFlag) {
		this.changeReasonFlag = changeReasonFlag;
	}

	public String getChomeTownFlag() {
		return this.chomeTownFlag;
	}

	public void setChomeTownFlag(String chomeTownFlag) {
		this.chomeTownFlag = chomeTownFlag;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityNameKana() {
		return this.cityNameKana;
	}

	public void setCityNameKana(String cityNameKana) {
		this.cityNameKana = cityNameKana;
	}

	public String getHasMultipleTownFlag() {
		return this.hasMultipleTownFlag;
	}

	public void setHasMultipleTownFlag(String hasMultipleTownFlag) {
		this.hasMultipleTownFlag = hasMultipleTownFlag;
	}

	public String getIssuedPerKoazaFlag() {
		return this.issuedPerKoazaFlag;
	}

	public void setIssuedPerKoazaFlag(String issuedPerKoazaFlag) {
		this.issuedPerKoazaFlag = issuedPerKoazaFlag;
	}

	public String getJapaneseLocalGovermentCode() {
		return this.japaneseLocalGovermentCode;
	}

	public void setJapaneseLocalGovermentCode(String japaneseLocalGovermentCode) {
		this.japaneseLocalGovermentCode = japaneseLocalGovermentCode;
	}

	public String getOldZipCode() {
		return this.oldZipCode;
	}

	public void setOldZipCode(String oldZipCode) {
		this.oldZipCode = oldZipCode;
	}

	public String getPrefectureName() {
		return this.prefectureName;
	}

	public void setPrefectureName(String prefectureName) {
		this.prefectureName = prefectureName;
	}

	public String getPrefectureNameKana() {
		return this.prefectureNameKana;
	}

	public void setPrefectureNameKana(String prefectureNameKana) {
		this.prefectureNameKana = prefectureNameKana;
	}

	public String getTownDevidedFlag() {
		return this.townDevidedFlag;
	}

	public void setTownDevidedFlag(String townDevidedFlag) {
		this.townDevidedFlag = townDevidedFlag;
	}

	public String getTownName() {
		return this.townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public String getTownNameKana() {
		return this.townNameKana;
	}

	public void setTownNameKana(String townNameKana) {
		this.townNameKana = townNameKana;
	}

	public String getUpdateDisplayFlag() {
		return this.updateDisplayFlag;
	}

	public void setUpdateDisplayFlag(String updateDisplayFlag) {
		this.updateDisplayFlag = updateDisplayFlag;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}