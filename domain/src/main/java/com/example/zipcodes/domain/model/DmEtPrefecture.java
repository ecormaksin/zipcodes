package com.example.zipcodes.domain.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.example.zipcodes.domain.type.prefecture.PrefectureCode;
import com.example.zipcodes.domain.type.prefecture.PrefectureName;
import com.example.zipcodes.domain.type.prefecture.PrefectureNameKana;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class DmEtPrefecture {

	/*
	@NotNull
	private String prefectureCode;

	@NotNull
	private String prefectureName;

	@NotNull
	private String prefectureNameKana;
	*/

	@Valid
	@NotNull
	private PrefectureCode prefectureCode;

	@Valid
	@NotNull
	private PrefectureName prefectureName;

	@Valid
	@NotNull
	private PrefectureNameKana prefectureNameKana;
}
