package com.practice.r2dbc.config;

import com.practice.r2dbc.db.internal.InternalConfigurationProperties;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.DefaultReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.dialect.PostgresDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.core.DatabaseClient;

import static io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD;
import static io.r2dbc.spi.ConnectionFactoryOptions.USER;


@Configuration
@EnableR2dbcRepositories(basePackages = "com.practice.r2dbc.db.internal", entityOperationsRef = "internalR2dbcEntityOperations")
public class InternalDBConfiguration {

    @Bean
    @Qualifier("internalConnectionFactory")
    @ConfigurationProperties("spring.datasource.internal")
    public ConnectionFactory internalConnectionFactory(InternalConfigurationProperties internalConfigurationProperties) {
        ConnectionFactoryOptions baseOptions
                = ConnectionFactoryOptions.parse(internalConfigurationProperties.getUrl());
        ConnectionFactoryOptions connectionBuilder
                = ConnectionFactoryOptions.builder()
                                          .from(baseOptions)
                                          .option(USER, internalConfigurationProperties.getUsername())
                                          .option(PASSWORD, internalConfigurationProperties.getPassword())
                                          .build();
        return ConnectionFactories.get(connectionBuilder);
    }


    @Bean("internalR2dbcEntityOperations")
    public R2dbcEntityOperations internalR2dbcEntityOperations(@Qualifier("internalConnectionFactory") ConnectionFactory connectionFactory) {
        DefaultReactiveDataAccessStrategy strategy = new DefaultReactiveDataAccessStrategy(PostgresDialect.INSTANCE);
        DatabaseClient databaseClient = DatabaseClient.builder()
                                                      .connectionFactory(connectionFactory)
                                                      .bindMarkers(PostgresDialect.INSTANCE.getBindMarkersFactory())
                                                      .build();

        return new R2dbcEntityTemplate(databaseClient, strategy);
    }
}
