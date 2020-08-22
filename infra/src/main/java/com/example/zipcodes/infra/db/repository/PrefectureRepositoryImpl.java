package com.example.zipcodes.infra.db.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.zipcodes.domain.model.DmEtPrefecture;
import com.example.zipcodes.infra.db.jpa.mapper.PrefectureMapper;
import com.example.zipcodes.infra.db.jpa.repository.PrefectureJpaRepository;
import com.example.zipcodes.infra.db.jpa.view.Prefecture;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PrefectureRepositoryImpl {

	private final PrefectureJpaRepository prefectureJpaRepository;
	private final PrefectureMapper prefectureMapper;

	public List<DmEtPrefecture> findAll() {

		List<Prefecture> entityList = prefectureJpaRepository.findAll();

		return prefectureMapper.fromEntityListToDomainObjectList(entityList);
	}
}
