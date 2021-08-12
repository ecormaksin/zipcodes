package com.example.zipcodes.domain.model.prefecture;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class PrefectureKatakanaFullwidthName extends PrefectureKanaName {

	private static final long serialVersionUID = 1L;

	public PrefectureKatakanaFullwidthName(final String value) {
		super(value);
	}
}
