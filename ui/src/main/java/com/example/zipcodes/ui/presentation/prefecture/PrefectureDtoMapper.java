package com.example.zipcodes.ui.presentation.prefecture;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.zipcodes.domain.model.prefecture.DmEtPrefecture;

@Component
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PrefectureDtoMapper {

	default PrefectureDto fromDomainObjectToDto(DmEtPrefecture entity) {

		// @formatter:off
		return PrefectureDto.builder()
				.code( entity.getCode().getValue() )
				.kanjiName( entity.getKanjiName().getValue() )
				.hiraganaName( entity.getHiraganaName().getValue() )
				.katakanaFullwidthName( entity.getKatakanaFullwidthName().getValue() )
				.katakanaHalfwidthName( entity.getKatakanaHalfwidthName().getValue() )
				.build();
		// @formatter:on
	}

	List<PrefectureDto> fromDomainObjectListToDtoList(List<DmEtPrefecture> entityList);
}
