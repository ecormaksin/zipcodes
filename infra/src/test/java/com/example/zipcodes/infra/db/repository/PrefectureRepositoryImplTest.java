package com.example.zipcodes.infra.db.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.example.zipcodes.infra.db.jpa.view.Prefecture;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
//@AutoConfigureTestEntityManager
class PrefectureRepositoryImplTest {

	@Autowired
	private PrefectureRepositoryImpl prefectureRepositoryImpl;

	@CustomJpaTest
	static class TestConfiguration {
	}

	@Test
	void _都道府県リストは47件() {

		List<Prefecture> list = prefectureRepositoryImpl.findAll();

		assertEquals(47, list.size());
	}

}
