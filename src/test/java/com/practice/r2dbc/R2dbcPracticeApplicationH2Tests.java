package com.practice.r2dbc;

import com.practice.r2dbc.db.common.UserAuditRepository;
import com.practice.r2dbc.db.internal.UserProfileRepository;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import java.time.Duration;

@Slf4j
@SpringBootApplication
class R2dbcPracticeApplicationH2Tests {

    @Autowired
            @Qualifier("internalR2dbcEntityOperations")
    R2dbcEntityOperations internalR2dbcEntityOperations;


    @Autowired
    @Qualifier("commonR2dbcEntityOperations")
    R2dbcEntityOperations commonR2dbcEntityOperations;



    public static void main(String[] args) {

        SpringApplication.run(R2dbcPracticeApplicationH2Tests.class, args);
    }

    @Bean
    public CommandLineRunner cmrUserLimit(UserAuditRepository userAuditRepository, UserProfileRepository userProfileRepository) {
        return (args) -> {


            log.info("user audits found with findAll():");
            userAuditRepository.findAll().doOnNext(userAudit -> {
//                log.info(userLimit.toString());
//                userAudit.setComments("new added");
//                commonR2dbcEntityOperations.update(userAudit);
                commonR2dbcEntityOperations.delete(userAudit);
            }).blockLast(Duration.ofSeconds(10));


            userAuditRepository.findAll().doOnNext(userAudit -> {
                log.info(userAudit.toString());
            }).blockLast(Duration.ofSeconds(10));

            userProfileRepository.findAll().doOnNext(userProfile -> {
                log.info(userProfile.toString());
            }).blockLast(Duration.ofSeconds(10));
        };
    }
}
