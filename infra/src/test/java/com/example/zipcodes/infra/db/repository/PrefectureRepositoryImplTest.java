package com.example.zipcodes.infra.db.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import com.example.zipcodes.infra.db.jpa.view.Prefecture;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
//@AutoConfigureTestEntityManager
class PrefectureRepositoryImplTest {

	@Autowired
	private PrefectureRepositoryImpl prefectureRepositoryImpl;

	@SpringBootApplication(scanBasePackages = {"com.example.zipcodes.infra"})
	@EntityScan(basePackages = {"com.example.zipcodes.infra.db.jpa.view"})
	@EnableJpaRepositories(basePackages = {"com.example.zipcodes.infra.db.jpa.repository"})
	static class TestConfiguration {
	}

	@Test
	void _都道府県リストは47件() {

		List<Prefecture> list = prefectureRepositoryImpl.findAll();

		assertEquals(47, list.size());
	}

}
