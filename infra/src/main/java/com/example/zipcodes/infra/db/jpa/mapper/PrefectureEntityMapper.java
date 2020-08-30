package com.example.zipcodes.infra.db.jpa.mapper;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.zipcodes.domain.model.prefecture.DmEtPrefecture;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.domain.model.prefecture.PrefectureHiraganaName;
import com.example.zipcodes.domain.model.prefecture.PrefectureKanjiName;
import com.example.zipcodes.domain.model.prefecture.PrefectureKatakanaFullwidthName;
import com.example.zipcodes.domain.model.prefecture.PrefectureKatakanaHalfwidthName;
import com.example.zipcodes.infra.db.jpa.view.Prefecture;
import com.ibm.icu.text.Transliterator;

@Component
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PrefectureEntityMapper {

	default DmEtPrefecture fromEntityToDomainObject(Prefecture entity) {

		final Transliterator hiraganaTransliterator = Transliterator.getInstance("Katakana-Hiragana");
		final Transliterator katakanaTransliterator = Transliterator.getInstance("Halfwidth-Fullwidth");

		final String kanaHalfwidth = entity.getPrefectureNameKana();
		final String kanaFullwidth = katakanaTransliterator.transliterate(kanaHalfwidth);
		final String hiragana = hiraganaTransliterator.transliterate(kanaFullwidth);

		// @formatter:off
		return DmEtPrefecture.builder()
				.code(new PrefectureCode(entity.getPrefectureCode()))
				.kanjiName(new PrefectureKanjiName(entity.getPrefectureName()))
				.hiraganaName(new PrefectureHiraganaName(hiragana))
				.katakanaFullwidthName(new PrefectureKatakanaFullwidthName(kanaFullwidth))
				.katakanaHalfwidthName(new PrefectureKatakanaHalfwidthName(kanaHalfwidth))
				.build();
		// @formatter:on
	}

	List<DmEtPrefecture> fromEntityListToDomainObjectList(List<Prefecture> entityList);
}
