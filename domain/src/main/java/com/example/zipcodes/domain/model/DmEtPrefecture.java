package com.example.zipcodes.domain.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.example.zipcodes.domain.type.prefecture.PrefectureCode;
import com.example.zipcodes.domain.type.prefecture.PrefectureName;
import com.example.zipcodes.domain.type.prefecture.PrefectureNameKana;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
public class DmEtPrefecture {

	@Valid
	@NotNull
	private PrefectureCode prefectureCode;

	@Setter
	@Valid
	@NotNull
	private PrefectureName prefectureName;

	@Setter
	@Valid
	@NotNull
	private PrefectureNameKana prefectureNameKana;

	public String toString() {
		// @formatter:off
		return String.format(
				"{\"prefectureCode\": \"%s\", "
				+ "\"prefectureName\": \"%s\", "
				+ "\"prefectureNameKana\": \"%s\"}",
				prefectureCode.getValue(),
				prefectureName.getValue(),
				prefectureNameKana.getValue());
		// @formatter:on
	}
}
