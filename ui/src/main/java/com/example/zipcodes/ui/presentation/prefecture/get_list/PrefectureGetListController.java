package com.example.zipcodes.ui.presentation.prefecture.get_list;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.zipcodes.domain.model.prefecture.DmEtPrefecture;
import com.example.zipcodes.domain.usecase.prefecture.PrefectureGetListUseCase;
import com.example.zipcodes.ui.presentation.prefecture.PrefectureDto;
import com.example.zipcodes.ui.presentation.prefecture.PrefectureDtoMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PrefectureGetListController {

	private final PrefectureGetListUseCase prefectureGetListUseCase;
	private final PrefectureDtoMapper prefectureMapper;

	@GetMapping("/prefectures")
	List<PrefectureDto> getList() {

		List<DmEtPrefecture> domainEntities = prefectureGetListUseCase.findAll();

		return prefectureMapper.fromDomainObjectListToDtoList(domainEntities);
	}
}
