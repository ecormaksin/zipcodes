package com.example.zipcodes.ui.presentation.prefecture.get_list;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	ResponseEntity<List<PrefectureDto>> getList() {

		List<DmEtPrefecture> domainEntities = prefectureGetListUseCase.findAll();

		List<PrefectureDto> list = prefectureMapper.fromDomainObjectListToDtoList(domainEntities);

	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));

	    return new ResponseEntity<>(list, httpHeaders, HttpStatus.OK);
	}
}
