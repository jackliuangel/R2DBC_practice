package com.practice.r2dbc.config;

import com.practice.r2dbc.db.common.CommonConfigurationProperties;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.r2dbc.core.DefaultReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.dialect.PostgresDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.core.DatabaseClient;

import static io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD;
import static io.r2dbc.spi.ConnectionFactoryOptions.USER;


@Configuration
@EnableR2dbcRepositories(basePackages = "com.practice.r2dbc.db.common", entityOperationsRef = "commonR2dbcEntityOperations")
public class CommonDBConfiguration {

    @Bean
    @Qualifier("commonConnectionFactory")
    public ConnectionFactory commonConnectionFactory(CommonConfigurationProperties commonConfigurationProperties) {
        ConnectionFactoryOptions baseOptions
                = ConnectionFactoryOptions.parse(commonConfigurationProperties.getUrl());
        ConnectionFactoryOptions connectionBuilder
                = ConnectionFactoryOptions.builder()
                                          .from(baseOptions)
                                          .option(USER, commonConfigurationProperties.getUsername())
                                          .option(PASSWORD, commonConfigurationProperties.getPassword())
                                          .build();
        return ConnectionFactories.get(connectionBuilder);
    }


    @Primary
    @Bean("commonR2dbcEntityOperations")
    public R2dbcEntityOperations commonR2dbcEntityOperations(@Qualifier("commonConnectionFactory") ConnectionFactory connectionFactory) {
        DefaultReactiveDataAccessStrategy strategy = new DefaultReactiveDataAccessStrategy(PostgresDialect.INSTANCE);
        DatabaseClient databaseClient = DatabaseClient.builder()
                                                      .connectionFactory(connectionFactory)
                                                      .bindMarkers(PostgresDialect.INSTANCE.getBindMarkersFactory())
                                                      .build();

        return new R2dbcEntityTemplate(databaseClient, strategy);
    }

}
