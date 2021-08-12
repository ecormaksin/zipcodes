package com.example.zipcodes.ui.usecase.prefecture;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zipcodes.domain.model.prefecture.DmEtPrefecture;
import com.example.zipcodes.domain.model.prefecture.PrefectureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrefectureGetListUseCase {

	private final PrefectureRepository prefectureRepository;

	public List<DmEtPrefecture> findAll() {

		return prefectureRepository.findAll();
	}
}
