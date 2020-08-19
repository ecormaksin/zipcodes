package com.example.zipcodes.infra.db.jpa.view;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PREFECTURES database table.
 * 
 */
@Entity
@Table(name="PREFECTURES")
@NamedQuery(name="Prefecture.findAll", query="SELECT p FROM Prefecture p")
public class Prefecture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PREFECTURE_CODE", insertable=false, updatable=false)
	private String prefectureCode;

	@Column(name="PREFECTURE_NAME")
	private String prefectureName;

	@Column(name="PREFECTURE_NAME_KANA")
	private String prefectureNameKana;

	public Prefecture() {
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