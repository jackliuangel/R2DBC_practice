package com.practice.r2dbc.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
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

    @Autowired
    private ConfigurableApplicationContext context;

    @Bean
    @Qualifier("internalDatasource")
    @ConfigurationProperties("spring.datasource.internal")
    public ConnectionFactory internalConnectionFactory() {
        ConnectionFactoryOptions baseOptions
                = ConnectionFactoryOptions.parse("r2dbc:postgresql://localhost:5432/vpn_port_db");
        ConnectionFactoryOptions connectionBuilder
                = ConnectionFactoryOptions.builder()
                                          .from(baseOptions)
                                          .option(USER, "admin").option(PASSWORD, "admin").build();
        return ConnectionFactories.get(connectionBuilder);
    }


    @Bean
    public R2dbcEntityOperations internalR2dbcEntityOperations(@Qualifier("internalDatasource") ConnectionFactory connectionFactory) {
        DefaultReactiveDataAccessStrategy strategy = new DefaultReactiveDataAccessStrategy(PostgresDialect.INSTANCE);
        DatabaseClient databaseClient = DatabaseClient.builder()
                                                      .connectionFactory(connectionFactory)
                                                      .bindMarkers(PostgresDialect.INSTANCE.getBindMarkersFactory())
                                                      .build();

        return new R2dbcEntityTemplate(databaseClient, strategy);
    }
}
