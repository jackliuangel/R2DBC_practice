package com.practice.r2dbc;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 * Define the query methods By deriving the query from the method name directly.
 * */
interface AccountRepository extends ReactiveCrudRepository<Account, Long> {
    @Query("SELECT * FROM account WHERE last_name = :lastname")
    Flux<Account> findByLastname(String lastName);

    Flux<Account> findTop2ByOrderByBalanceDesc();

    Flux<Account> findByOwnerStartingWith(String owner);

    Mono<Long> deleteByAccountNumber(String accountNumber);
}