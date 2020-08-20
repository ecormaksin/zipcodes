package com.example.zipcodes.infra.db.repository;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Retention(RUNTIME)
@Target(TYPE)
@Documented
@Inherited
@SpringBootApplication(scanBasePackages = {"com.example.zipcodes.infra"})
@EntityScan(basePackages = {"com.example.zipcodes.infra.db.jpa.view"})
@EnableJpaRepositories(basePackages = {"com.example.zipcodes.infra.db.jpa.repository"})
public @interface CustomJpaTest {

}
