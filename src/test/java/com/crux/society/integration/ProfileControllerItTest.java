package com.crux.society.integration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import com.crux.society.models.RegisterProfileDtoModel;
import com.crux.society.models.RegisterProfileResponseDto;
import com.crux.society.models.RegisterProfileResponseDtoModel;
import com.crux.society.utils.BaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

public class ProfileControllerItTest extends BaseIntegrationTest {

  @Autowired private WebTestClient webTestClient;

  @Test
  @DisplayName("ProfileController#registerProfile should return RegisterProfileResponseDto")
  public void profileControllerPostProfile() {
    var dto = RegisterProfileDtoModel.basic();
    var expected = RegisterProfileResponseDtoModel.basic();

    var response =
        webTestClient
            .post()
            .uri("/society/register")
            .contentType(APPLICATION_JSON)
            .body(fromValue(dto))
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(RegisterProfileResponseDto.class)
            .returnResult();

    var responseBody = response.getResponseBody();

    assertThat(responseBody).isEqualTo(expected);
  }
}
