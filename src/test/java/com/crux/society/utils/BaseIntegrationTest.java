package com.crux.society.utils;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BaseIntegrationTest {

  @Container
  private static final PostgreSQLContainer<?> POSTGRES_CONTAINER =
      new PostgreSQLContainer<>("postgres:15-alpine")
          .withDatabaseName("profile")
          .withUsername("Tomasz")
          .withPassword("abc");

  @DynamicPropertySource
  public static void containerConfig(DynamicPropertyRegistry registry) {
    registry.add(
        "spring.r2dbc.url", () -> POSTGRES_CONTAINER.getJdbcUrl().replace("jdbc", "r2dbc")
    );
    registry.add("spring.r2dbc.username", POSTGRES_CONTAINER::getUsername);
    registry.add("spring.r2dbc.password", POSTGRES_CONTAINER::getPassword);
  }
}
