package com.example.zipcodes.domain.model.prefecture;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class PrefectureKatakanaHalfwidthName extends PrefectureKanaName {

	private static final long serialVersionUID = 1L;

	public PrefectureKatakanaHalfwidthName(final String value) {
		super(value);
	}
}
