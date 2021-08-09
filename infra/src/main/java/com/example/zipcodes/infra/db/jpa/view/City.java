package com.example.zipcodes.infra.db.jpa.view;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CITIES database table.
 * 
 */
@Entity
@Table(schema = "ZIP_CODE", name="CITIES")
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CITY_NAME", insertable=false, updatable=false)
	private String cityName;

	@Column(name="CITY_NAME_KANA", insertable=false, updatable=false)
	private String cityNameKana;

	@Id
	@Column(name="JAPANESE_LOCAL_GOVERMENT_CODE", insertable=false, updatable=false)
	private String japaneseLocalGovermentCode;

	@Column(name="PREFECTURE_CODE")
	private String prefectureCode;

	@Column(name="PREFECTURE_NAME")
	private String prefectureName;

	@Column(name="PREFECTURE_NAME_KANA")
	private String prefectureNameKana;

	public City() {
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

	public String getJapaneseLocalGovermentCode() {
		return this.japaneseLocalGovermentCode;
	}

	public void setJapaneseLocalGovermentCode(String japaneseLocalGovermentCode) {
		this.japaneseLocalGovermentCode = japaneseLocalGovermentCode;
	}

	public String getPrefectureCode() {
		return this.prefectureCode;
	}

	public void setPrefectureCode(String prefectureCode) {
		this.prefectureCode = prefectureCode;
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

}