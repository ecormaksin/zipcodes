package com.example.zipcodes.infra.db.jpa.mapper;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.zipcodes.domain.model.prefecture.DmEtPrefecture;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.domain.model.prefecture.PrefectureName;
import com.example.zipcodes.domain.model.prefecture.PrefectureNameKana;
import com.example.zipcodes.infra.db.jpa.view.Prefecture;
import com.ibm.icu.text.Transliterator;

@Component
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class PrefectureEntityMapper {

	public DmEtPrefecture fromEntityToDomainObject(Prefecture entity) {

		Transliterator transliterator = Transliterator.getInstance("Halfwidth-Fullwidth");

		final String kanaHalfwidth = entity.getPrefectureNameKana();
		final String kanaFullwidth = transliterator.transliterate(kanaHalfwidth);

		// @formatter:off
		return DmEtPrefecture.builder()
				.prefectureCode(new PrefectureCode(entity.getPrefectureCode()))
				.prefectureName(new PrefectureName(entity.getPrefectureName()))
				.prefectureNameKana(new PrefectureNameKana(kanaFullwidth))
				.build();
		// @formatter:on
	}

	public abstract List<DmEtPrefecture> fromEntityListToDomainObjectList(List<Prefecture> entityList);
}
