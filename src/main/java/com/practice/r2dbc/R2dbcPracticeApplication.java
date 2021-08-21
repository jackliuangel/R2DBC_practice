package com.practice.r2dbc;

import com.practice.r2dbc.db.common.UserAuditRepository;
import com.practice.r2dbc.db.internal.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;

//TODO: refer to
//https://github.com/hantsy/spring-puzzles/blob/master/multi-r2dbc-connectionfactories/src/main/java/com/example/demo/customers/CustomerConfig.java

@SpringBootApplication
public class R2dbcPracticeApplication {

    private static final Logger log = LoggerFactory.getLogger(R2dbcPracticeApplication.class);

    public static void main(String[] args) {

//		ConnectionFactory connectionFactory = ConnectionFactories.get("r2dbc:h2:mem:///test?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
//
//		R2dbcEntityTemplate template = new R2dbcEntityTemplate(connectionFactory);
//template.getDatabaseClient().

        SpringApplication.run(R2dbcPracticeApplication.class, args);
    }

//	@Bean
//	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
//
////		ConnectionFactory connectionFactory = ConnectionFactories.get("r2dbc:h2:mem:///test?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
//
//
//		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
//		initializer.setConnectionFactory(connectionFactory);
//		initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
//		return initializer;
//	}

//    @Bean
//    public CommandLineRunner demo(CustomerRepository repository) {
//
//        return (args) -> {
//            // save a few customers
//            repository.saveAll(Arrays.asList(new Customer("Jack", "Bauer"),
//                new Customer("Chloe", "O'Brian"),
//                new Customer("Kim", "Bauer"),
//                new Customer("David", "Palmer"),
//                new Customer("Michelle", "Dessler")))
//                .blockLast(Duration.ofSeconds(10));
//
//            // fetch all customers
//            log.info("Customers found with findAll():");
//            log.info("-------------------------------");
//            repository.findAll().doOnNext(customer -> {
//                log.info(customer.toString());
//            }).blockLast(Duration.ofSeconds(10));
//
//            log.info("");
//
//            // fetch an individual customer by ID
//			repository.findById(1L).doOnNext(customer -> {
//				log.info("Customer found with findById(1L):");
//				log.info("--------------------------------");
//				log.info(customer.toString());
//				log.info("");
//			}).block(Duration.ofSeconds(10));
//
//            // fetch customers by last name
//            log.info("Customer found with findByLastName('Bauer'):");
//            log.info("--------------------------------------------");
//            repository.findByLastName("Bauer").doOnNext(bauer -> {
//                log.info(bauer.toString());
//            }).blockLast(Duration.ofSeconds(10));;
//            log.info("");
//        };
//    }

    // cmr runs the methods in AccountRepository and write the log

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

    //    @Bean
    public CommandLineRunner cmr(AccountRepository repository) {
        return (args) -> {


            repository.saveAll(Arrays.asList(
                    new Account("123456789", "Jack", "Bauer", new BigDecimal(500.5)),
                    new Account("987654327", "Chloe", "Brian", new BigDecimal(10000000)),
                    new Account("123454127", "Kim", "Bauer", new BigDecimal(99999)),
                    new Account("772345632", "David", "Palmer", new BigDecimal(300)),
                    new Account("876434544", "David", "Xuani", new BigDecimal(300)),
                    new Account("123123123", "David", "Hoi", new BigDecimal(300)),
                    new Account("876543442", "Michelle", "Dessler", new BigDecimal(400))
            )).blockLast(Duration.ofSeconds(10));

            log.info("Accounts found with findAll():");
            repository.findAll().doOnNext(account -> {
                log.info(account.toString());
            }).blockLast(Duration.ofSeconds(10));

            log.info("");

            repository.findById(1L).doOnNext(account -> {
                log.info("Accounts found with findById(1L):");
                log.info(account.toString());
            }).block(Duration.ofSeconds(10));

            log.info("findByLastName");
            repository.findByLastname("Bauer").doOnNext(bauer -> {
                log.info(bauer.toString());
                ;
            }).blockLast(Duration.ofSeconds(10));

            log.info("findByTop2Balance");
            repository.findTop2ByOrderByBalanceDesc().doOnNext(account -> {
                log.info(account.toString());
            }).blockLast(Duration.ofSeconds(10));

            log.info("findByOwnerStartingWith");
            repository.findByOwnerStartingWith("Dav").doOnNext(account -> {
                log.info(account.toString());
            }).blockLast(Duration.ofSeconds(10));

            log.info("deleteByAccountNumber");
            repository.deleteByAccountNumber("876543442").doOnNext(v -> {
                log.info(v.toString());
            }).block(Duration.ofSeconds(10));
        };
    }
}
