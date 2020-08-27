
package com.example.zipcodes.ui.presentation.prefecture.get_list;

import java.io.Serializable;
import java.util.List;

import com.example.zipcodes.ui.presentation.prefecture.PrefectureDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "prefectureList"
})
@Data
public class PrefectureGetListResponse implements Serializable {

    private final static long serialVersionUID = 1L;

    @JsonProperty("prefectureList")
    private List<PrefectureDto> prefectureList = null;
}
