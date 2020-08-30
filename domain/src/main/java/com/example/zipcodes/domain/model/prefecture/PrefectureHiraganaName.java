package com.example.zipcodes.domain.model.prefecture;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class PrefectureHiraganaName extends PrefectureKanaName {

    public PrefectureHiraganaName(final String value) {
        super(value);
    }
}
