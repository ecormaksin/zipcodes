package com.example.zipcodes.ui.presentation.prefecture;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code",
    "kanjiName",
    "hiraganaName",
    "katakanaFullwidthName",
    "katakanaHalfwidthName"
})
@Data
@Builder
public class PrefectureDto implements Serializable {

	private static final long serialVersionUID = 1L;

    @JsonProperty("code")
    private String code;

    @JsonProperty("kanjiName")
    private String kanjiName;

    @JsonProperty("hiraganaName")
    private String hiraganaName;

    @JsonProperty("katakanaFullwidthName")
    private String katakanaFullwidthName;

    @JsonProperty("katakanaHalfwidthName")
    private String katakanaHalfwidthName;
}
