package com.example.zipcodes.domain.model.prefecture;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class PrefectureKatakanaHalfwidthName extends PrefectureKanaName {

    public PrefectureKatakanaHalfwidthName(final String value) {
        super(value);
    }
}
