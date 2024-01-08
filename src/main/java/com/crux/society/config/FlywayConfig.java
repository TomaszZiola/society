package com.crux.society.config;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import static org.flywaydb.core.Flyway.configure;

@Configuration
@RequiredArgsConstructor
public class FlywayConfig {

    private final Environment env;

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        return new Flyway(configure()
                .dataSource(
                        env.getRequiredProperty("spring.flyway.url"),
                        env.getRequiredProperty("spring.flyway.user"),
                        env.getRequiredProperty("spring.flyway.password"))
        );
    }
}
