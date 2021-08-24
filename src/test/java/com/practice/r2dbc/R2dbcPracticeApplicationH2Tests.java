package com.practice.r2dbc;

import com.practice.r2dbc.db.common.UserAuditRepository;
import com.practice.r2dbc.db.internal.UserProfileRepository;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import java.time.Duration;

@Slf4j
@SpringBootApplication
class R2dbcPracticeApplicationH2Tests {


    public static void main(String[] args) {

        SpringApplication.run(R2dbcPracticeApplicationH2Tests.class, args);
    }

    @Bean
    public CommandLineRunner cmrUserLimit(UserAuditRepository repository, UserProfileRepository userProfileRepository) {
        return (args) -> {
            log.info("userlimit found with findAll():");
            repository.findAll().doOnNext(userLimit -> {
                log.info(userLimit.toString());
            }).blockLast(Duration.ofSeconds(10));

            userProfileRepository.findAll().doOnNext(userLimit -> {
                log.info(userLimit.toString());
            }).blockLast(Duration.ofSeconds(10));
        };
    }
}
