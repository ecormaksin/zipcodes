package com.example.zipcodes.infra.db.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import com.example.zipcodes.domain.model.prefecture.DmEtPrefecture;
import com.example.zipcodes.infra.db.jpa.mapper.PrefectureMapper;
import com.example.zipcodes.infra.db.jpa.view.Prefecture;
import com.example.zipcodes.infra.db.jpa.view.QPrefecture;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PrefectureRepositoryImpl {

    private final EntityManager entityManager;
	private final PrefectureMapper prefectureMapper;

    private JPAQueryFactory queryFactory;

    @PostConstruct
    void postConstruct() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

	public List<DmEtPrefecture> findAll() {

		QPrefecture qPrefecture = QPrefecture.prefecture;
		// @formatter:off
		List<Prefecture> entityList = queryFactory
				.selectFrom(qPrefecture)
				.orderBy(qPrefecture.prefectureCode.asc())
				.fetch();
		// @formatter:on

		return prefectureMapper.fromEntityListToDomainObjectList(entityList);
	}
}
