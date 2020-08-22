package com.example.zipcodes.infra.db.querydsl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.sql.OracleTemplates;
import com.querydsl.sql.SQLTemplates;

@Configuration
public class SQLTemplatesConfig {

    @Bean
    SQLTemplates sqlTemplatesOracle() {
        return new OracleTemplates();
    }
}
