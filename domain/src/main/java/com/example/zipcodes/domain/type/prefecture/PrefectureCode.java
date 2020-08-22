package com.example.zipcodes.domain.type.prefecture;

import com.example.zipcodes.domain.type.ValidationConcern;
import com.example.zipcodes.domain.type.validation.notallblank.NotAllBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class PrefectureCode extends ValidationConcern {

	public static final int LENGTH = 2;

    @NotAllBlank(min = LENGTH, max = LENGTH)
    private final String value;

    public PrefectureCode(final String value) {
        this.value = value;
        this.validate(this);
    }
}
