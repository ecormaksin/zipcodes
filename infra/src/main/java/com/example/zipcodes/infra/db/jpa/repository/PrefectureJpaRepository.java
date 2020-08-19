package com.example.zipcodes.infra.db.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.zipcodes.infra.db.jpa.view.Prefecture;

@Repository
public interface PrefectureJpaRepository extends JpaRepository<Prefecture, String> {

}
