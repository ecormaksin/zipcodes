package com.example.zipcodes.ui.presentation.prefecture.get_list;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.example.zipcodes.domain.model.prefecture.DmEtPrefecture;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.domain.model.prefecture.PrefectureHiraganaName;
import com.example.zipcodes.domain.model.prefecture.PrefectureKanjiName;
import com.example.zipcodes.domain.model.prefecture.PrefectureKatakanaFullwidthName;
import com.example.zipcodes.domain.model.prefecture.PrefectureKatakanaHalfwidthName;
import com.example.zipcodes.domain.usecase.prefecture.PrefectureGetListUseCase;
import com.example.zipcodes.ui.presentation.prefecture.PrefectureDto;
import com.example.zipcodes.ui.presentation.prefecture.PrefectureDtoMapper;
import com.example.zipcodes.ui.presentation.prefecture.PrefectureDtoMapperImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PrefectureGetListController.class)
@Import(PrefectureDtoMapperImpl.class)
class PrefectureGetListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrefectureGetListUseCase prefectureGetListUseCase;

    @Autowired
    private PrefectureDtoMapper prefectureMapper;

    private DmEtPrefecture tokyoto;
    private PrefectureDto tokyotoDto;

    private DmEtPrefecture kyotofu;
    private PrefectureDto kyotofuDto;

    @BeforeEach
    void beforeEach() {

		// @formatter:off
		tokyoto = DmEtPrefecture.builder()
				.code( new PrefectureCode("13") )
				.kanjiName( new PrefectureKanjiName("東京都") )
				.hiraganaName( new PrefectureHiraganaName("とうきょうと") )
				.katakanaFullwidthName( new PrefectureKatakanaFullwidthName("トウキョウト") )
				.katakanaHalfwidthName( new PrefectureKatakanaHalfwidthName("ﾄｳｷｮｳﾄ") )
				.build();
		tokyotoDto = prefectureMapper.fromDomainObjectToDto(tokyoto);

		kyotofu = DmEtPrefecture.builder()
				.code( new PrefectureCode("26") )
				.kanjiName( new PrefectureKanjiName("京都府") )
				.hiraganaName( new PrefectureHiraganaName("きょうとふ") )
				.katakanaFullwidthName( new PrefectureKatakanaFullwidthName("キョウトフ") )
				.katakanaHalfwidthName( new PrefectureKatakanaHalfwidthName("ｷｮｳﾄﾌ") )
				.build();
		kyotofuDto = prefectureMapper.fromDomainObjectToDto(kyotofu);
		// @formatter:on
    }

	@Test
	void 都道府県コード指定なしの時はリストが返ってくる() throws Exception {

		List<DmEtPrefecture> dmEtList = new ArrayList<>();
		dmEtList.add( tokyoto );
		dmEtList.add( kyotofu );

		List<PrefectureDto> expected = new ArrayList<>();
		expected.add( tokyotoDto );
		expected.add( kyotofuDto );

		ObjectMapper objectMapper = new ObjectMapper();
		final String expectedString = objectMapper.writeValueAsString(expected);

		when(prefectureGetListUseCase.findAll()).thenReturn(dmEtList);

		// @formatter:off
		mockMvc.perform(get("/prefectures"))
			.andExpect(status().isOk())
			.andExpect(content().string(expectedString));
		// @formatter:on
	}

}
