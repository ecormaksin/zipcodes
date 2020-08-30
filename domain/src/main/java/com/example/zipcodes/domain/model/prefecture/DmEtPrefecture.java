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
	private PrefectureCode code;

	@Setter
	@Valid
	@NotNull
	private PrefectureKanjiName kanjiName;

	@Setter
	@Valid
	@NotNull
	private PrefectureHiraganaName hiraganaName;

	@Setter
	@Valid
	@NotNull
	private PrefectureKatakanaFullwidthName katakanaFullwidthName;

	@Setter
	@Valid
	@NotNull
	private PrefectureKatakanaHalfwidthName katakanaHalfwidthName;

	public String toString() {
		// @formatter:off
		return String.format(
				"\"dmEtPrefecture\": {\"code\": \"%s\", "
				+ "\"kanjiName\": \"%s\", "
				+ "\"hiraganaFullwidthName\": \"%s\"}"
				+ "\"katakanaHalfwidthName\": \"%s\"}"
				+ "\"katakanaHalfwidthName\": \"%s\"}"
				, code.getValue()
				, kanjiName.getValue()
				, hiraganaName.getValue()
				, katakanaFullwidthName.getValue()
				, katakanaHalfwidthName.getValue());
		// @formatter:on
	}
}
