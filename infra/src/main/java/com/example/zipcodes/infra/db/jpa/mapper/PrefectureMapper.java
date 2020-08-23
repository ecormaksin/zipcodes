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

@Component
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PrefectureMapper {

	default DmEtPrefecture fromEntityToDomainObject(Prefecture entity) {
		// @formatter:off
		return DmEtPrefecture.builder()
				.prefectureCode(new PrefectureCode(entity.getPrefectureCode()))
				.prefectureName(new PrefectureName(entity.getPrefectureName()))
				.prefectureNameKana(new PrefectureNameKana(entity.getPrefectureNameKana()))
				.build();
		// @formatter:on
	}

	List<DmEtPrefecture> fromEntityListToDomainObjectList(List<Prefecture> entityList);
}
