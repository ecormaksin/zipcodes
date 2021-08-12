package com.example.zipcodes.infra.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import com.example.zipcodes.domain.model.prefecture.DmEtPrefecture;
import com.example.zipcodes.domain.model.prefecture.PrefectureRepository;
import com.example.zipcodes.infra.db.jpa.mapper.PrefectureEntityMapper;
import com.example.zipcodes.infra.db.jpa.view.Prefecture;
import com.example.zipcodes.infra.db.jpa.view.QPrefecture;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PrefectureRepositoryImpl implements PrefectureRepository {

	private final EntityManager entityManager;
	private final PrefectureEntityMapper prefectureMapper;

	private JPAQueryFactory queryFactory;

	private QPrefecture qPrefecture = QPrefecture.prefecture;

	@PostConstruct
	void postConstruct() {
		queryFactory = new JPAQueryFactory(entityManager);
	}

	@Override
	public List<DmEtPrefecture> findAll() {

		// @formatter:off
		List<Prefecture> entityList = queryFactory
				.selectFrom(qPrefecture)
				.orderBy(qPrefecture.prefectureCode.asc())
				.fetch();
		// @formatter:on

		return prefectureMapper.fromEntityListToDomainObjectList(entityList);
	}

	@Override
	public DmEtPrefecture findByPrefectureCode(final String prefectureCode) {

		// @formatter:off
		Prefecture prefecture = queryFactory
				.selectFrom(qPrefecture)
				.where( qPrefecture.prefectureCode.eq(prefectureCode) )
				.fetchOne();
		// @formatter:on

		return prefectureMapper.fromEntityToDomainObject(prefecture);
	}
}
