package com.crux.society.integrations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatusCode.valueOf;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import com.crux.society.models.ProfileModel;
import com.crux.society.models.ProfileResponseDto;
import com.crux.society.models.ProfileResponseDtoModel;
import com.crux.society.models.RegisterProfileDtoModel;
import com.crux.society.repositories.ProfileRepository;
import com.crux.society.utils.BaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

public class ProfileControllerItTest extends BaseIntegrationTest {

  @Autowired private ProfileRepository repository;
  @Autowired private WebTestClient client;

  @Test
  @DisplayName("ProfileController#registerProfile should return ProfileResponseDto")
  public void profileControllerPostProfileTest() {
    // given
    var dto = RegisterProfileDtoModel.basic();
    var expected = ProfileResponseDtoModel.basic();

    // when
    var dtoBodySpec =
        client
            .post()
            .uri("/society/register")
            .contentType(APPLICATION_JSON)
            .body(fromValue(dto))
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(ProfileResponseDto.class);

    // then
    dtoBodySpec.consumeWith(response -> assertThat(response.getResponseBody()).isEqualTo(expected));
  }

  @Test
  @DisplayName("ProfileController#getProfile should return ProfileResponseDto")
  public void profileControllerGetProfileTest() {
    // given
    var expected = ProfileResponseDtoModel.basic();
    var profileId = repository.save(ProfileModel.basic()).getId();

    // when
    var dtoBodySpec =
        client
            .get()
            .uri("society/" + profileId)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(ProfileResponseDto.class);

    // then
    dtoBodySpec.consumeWith(response -> assertThat(response.getResponseBody()).isEqualTo(expected));
  }

  @Test
  @DisplayName("ProfileController#getProfile should return Exception when profile not found")
  public void profileControllerGetProfileNotFoundTest() {
    // when
    var dtoBodySpec =
            client
                    .get()
                    .uri("society/1")
                    .exchange()
                    .expectStatus()
                    .isNotFound()
                    .expectBody(ResponseEntity.class);

    // then
    dtoBodySpec.consumeWith(
        response -> assertThat(response.getStatus()).isEqualTo(valueOf(NOT_FOUND.value())));
  }
}
