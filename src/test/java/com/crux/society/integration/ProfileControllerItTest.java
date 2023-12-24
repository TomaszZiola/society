package com.crux.society.integration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static reactor.test.StepVerifier.create;

import com.crux.society.models.ProfileModel;
import com.crux.society.models.ProfileResponseDto;
import com.crux.society.models.RegisterProfileDtoModel;
import com.crux.society.models.ProfileResponseDtoModel;
import com.crux.society.repositories.ProfileRepository;
import com.crux.society.utils.BaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

public class ProfileControllerItTest extends BaseIntegrationTest {

  @Autowired private WebTestClient webTestClient;
  @Autowired private ProfileRepository repository;

  @Test
  @DisplayName("ProfileController#registerProfile should return ProfileResponseDto")
  public void profileControllerPostProfileTest() {
    // given
    var dto = RegisterProfileDtoModel.basic();
    var expected = ProfileResponseDtoModel.basic();

    // when
    var response =
        webTestClient
            .post()
            .uri("/society/register")
            .contentType(APPLICATION_JSON)
            .body(fromValue(dto))
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(ProfileResponseDto.class)
            .returnResult();

    create(repository.findById(1L))
        .expectSubscription()
        .expectNextMatches(
            profile -> profile.getId().equals(1L) && profile.getName().equals("Tomasz"))
        .verifyComplete();

    // then
    var responseBody = response.getResponseBody();

    assertThat(responseBody).isEqualTo(expected);
  }

  @Test
  @DisplayName("ProfileController#getProfile should return ProfileResponseDto")
  public void profileControllerGetProfileTest() {
    // given
    var expected = ProfileResponseDtoModel.basic();
    repository.save(ProfileModel.basic()).subscribe();

    // when
    var response =
            webTestClient
                    .get()
                    .uri("/society/1")
                    .exchange()
                    .expectStatus()
                    .isOk()
                    .expectBody(ProfileResponseDto.class)
                    .returnResult();

    // then
    var responseBody = response.getResponseBody();

    assertThat(responseBody).isEqualTo(expected);
  }
}
