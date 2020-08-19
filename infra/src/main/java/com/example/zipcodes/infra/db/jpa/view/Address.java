package com.example.zipcodes.infra.db.jpa.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the ADDRESSES database table.
 *
 */
@Entity
@Table(name="ADDRESSES")
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(insertable=false, updatable=false)
	private Long id;

	@Column(name="CITY_NAME", insertable=false, updatable=false)
	private String cityName;

	@Column(name="CITY_NAME_KANA", insertable=false, updatable=false)
	private String cityNameKana;

	@Column(name="PREFECTURE_NAME", insertable=false, updatable=false)
	private String prefectureName;

	@Column(name="PREFECTURE_NAME_KANA", insertable=false, updatable=false)
	private String prefectureNameKana;

	@Column(name="TOWN_NAME", insertable=false, updatable=false)
	private String townName;

	@Column(name="TOWN_NAME_KANA", insertable=false, updatable=false)
	private String townNameKana;

	@Column(name="ZIP_CODE", insertable=false, updatable=false)
	private String zipCode;

	public Address() {
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

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}