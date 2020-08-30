package com.example.zipcodes.domain.model.prefecture;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class PrefectureKatakanaFullwidthName extends PrefectureKanaName {

    public PrefectureKatakanaFullwidthName(final String value) {
        super(value);
    }
}
