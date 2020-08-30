package com.example.zipcodes.domain.model.prefecture;

import com.example.zipcodes.domain.model.ValidationConcern;
import com.example.zipcodes.domain.validation.notallblank.NotAllBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
abstract class PrefectureKanaName extends ValidationConcern {

	public static final int LENGTH_MAX = 10;

    @NotAllBlank(max = LENGTH_MAX)
    protected final String value;

    public PrefectureKanaName(final String value) {
        this.value = value;
        this.validate(this);
    }
}
