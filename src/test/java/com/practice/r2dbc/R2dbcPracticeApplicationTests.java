package com.practice.r2dbc;

import com.practice.r2dbc.db.common.UserAuditRepository;
import com.practice.r2dbc.db.internal.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

@Slf4j
@SpringBootTest
class R2dbcPracticeApplicationTests {

    @Autowired
    UserAuditRepository userAuditRepository;

    @Autowired
    UserProfileRepository userProfileRepository;


    @Test
    void contextLoads() {
        log.info("userAudit found with findAll():");
        userAuditRepository.findAll().doOnNext(userA -> {
            log.info(userA.toString());
        }).blockLast(Duration.ofSeconds(10));

        userProfileRepository.findAll().doOnNext(userProfile -> {
            log.info(userProfile.toString());
        }).blockLast(Duration.ofSeconds(10));
    }



}
