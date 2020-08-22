package com.example.zipcodes.infra.db.jpa.mapper;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.zipcodes.domain.model.DmEtPrefecture;
import com.example.zipcodes.domain.type.prefecture.PrefectureCode;
import com.example.zipcodes.domain.type.prefecture.PrefectureName;
import com.example.zipcodes.domain.type.prefecture.PrefectureNameKana;
import com.example.zipcodes.infra.db.jpa.view.Prefecture;

@Component
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PrefectureMapper {

	/*@Mappings({
		@Mapping(source = "prefectureCode", target = "prefectureCode.value"),
		@Mapping(source = "prefectureName", target = "prefectureName.value"),
		@Mapping(source = "prefectureNameKana", target = "prefectureNameKana.value")
	})*/
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