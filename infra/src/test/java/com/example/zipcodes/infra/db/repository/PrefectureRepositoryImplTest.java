package com.example.zipcodes.infra.db.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.example.zipcodes.domain.model.prefecture.DmEtPrefecture;
import com.example.zipcodes.domain.model.prefecture.PrefectureRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@Slf4j
@AutoConfigureTestEntityManager
class PrefectureRepositoryImplTest {

	@Autowired
	private PrefectureRepository prefectureRepositoryImpl;

	@CustomJpaTest
	static class TestConfiguration {
	}

	@Test
	void _都道府県リストは47件() {

		List<DmEtPrefecture> list = prefectureRepositoryImpl.findAll();

		assertEquals(47, list.size());

		for (DmEtPrefecture obj : list) {
			log.info(obj.toString());
		}
	}

}
