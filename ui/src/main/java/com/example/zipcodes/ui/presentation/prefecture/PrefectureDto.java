package com.example.zipcodes.ui.presentation.prefecture;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "prefectureCode",
    "prefectureName",
    "prefectureNameKana"
})
@Data
@Builder
public class PrefectureDto implements Serializable {

	private static final long serialVersionUID = 1L;

    @JsonProperty("prefectureCode")
    private String prefectureCode;

    @JsonProperty("prefectureName")
    private String prefectureName;

    @JsonProperty("prefectureNameKana")
    private String prefectureNameKana;
}
