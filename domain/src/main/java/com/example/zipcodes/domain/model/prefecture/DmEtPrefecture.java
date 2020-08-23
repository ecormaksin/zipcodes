package com.example.zipcodes.domain.model.prefecture;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
