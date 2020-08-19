package com.example.zipcodes.infra.db.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.zipcodes.infra.db.jpa.repository.PrefectureJpaRepository;
import com.example.zipcodes.infra.db.jpa.view.Prefecture;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PrefectureRepositoryImpl {

	private final PrefectureJpaRepository prefectureJpaRepository;

	public List<Prefecture> findAll() {
		return prefectureJpaRepository.findAll();
	}
}
