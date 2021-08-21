package com.practice.r2dbc;

import com.practice.r2dbc.db.common.UserAuditRepository;
import com.practice.r2dbc.db.internal.UserProfileRepository;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

//@Slf4j
@SpringBootTest
class R2dbcPracticeApplicationH2Tests {

    @Autowired
    UserAuditRepository userAuditRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

//
//    @Bean
//    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
//
//        ConnectionFactory connectionFactory = ConnectionFactories.get("r2dbc:h2:mem:///test?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
//
//
//        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
//        initializer.setConnectionFactory(connectionFactory);
//        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("resource/schema.sql")));
//        return initializer;
//    }


//    //    @Bean
//    public CommandLineRunner cmr(AccountRepository repository) {
//        return (args) -> {
//
//
//            repository.saveAll(Arrays.asList(
//                    new Account("123456789", "Jack", "Bauer", new BigDecimal(500.5)),
//                    new Account("987654327", "Chloe", "Brian", new BigDecimal(10000000)),
//                    new Account("123454127", "Kim", "Bauer", new BigDecimal(99999)),
//                    new Account("772345632", "David", "Palmer", new BigDecimal(300)),
//                    new Account("876434544", "David", "Xuani", new BigDecimal(300)),
//                    new Account("123123123", "David", "Hoi", new BigDecimal(300)),
//                    new Account("876543442", "Michelle", "Dessler", new BigDecimal(400))
//            )).blockLast(Duration.ofSeconds(10));
//
//            log.info("Accounts found with findAll():");
//            repository.findAll().doOnNext(account -> {
//                log.info(account.toString());
//            }).blockLast(Duration.ofSeconds(10));
//
//            log.info("");
//
//            repository.findById(1L).doOnNext(account -> {
//                log.info("Accounts found with findById(1L):");
//                log.info(account.toString());
//            }).block(Duration.ofSeconds(10));
//
//            log.info("findByLastName");
//            repository.findByLastname("Bauer").doOnNext(bauer -> {
//                log.info(bauer.toString());
//                ;
//            }).blockLast(Duration.ofSeconds(10));
//
//            log.info("findByTop2Balance");
//            repository.findTop2ByOrderByBalanceDesc().doOnNext(account -> {
//                log.info(account.toString());
//            }).blockLast(Duration.ofSeconds(10));
//
//            log.info("findByOwnerStartingWith");
//            repository.findByOwnerStartingWith("Dav").doOnNext(account -> {
//                log.info(account.toString());
//            }).blockLast(Duration.ofSeconds(10));
//
//            log.info("deleteByAccountNumber");
//            repository.deleteByAccountNumber("876543442").doOnNext(v -> {
//                log.info(v.toString());
//            }).block(Duration.ofSeconds(10));
//        };
//    }

}
