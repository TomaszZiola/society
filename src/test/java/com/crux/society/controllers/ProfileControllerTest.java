package com.crux.society.controllers;

import static org.mockito.Mockito.verify;
import static reactor.test.StepVerifier.create;

import com.crux.society.utils.BaseUnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfileControllerTest extends BaseUnitTest {

  @Test
  @DisplayName("ProfileController#registerProfile should return ProfileResponseDto")
  public void registerProfileTest() {
    // when then
    create(controller.registerProfile(registerProfileDto))
        .expectNext(profileResponseDto)
        .verifyComplete();

    verify(service).registerProfile(registerProfileDto);
  }

  @Test
  @DisplayName("ProfileController#getProfile should return ProfileResponseDto")
  public void getProfileTest() {
    // when then
    create(controller.getProfile(1L))
            .expectNext(profileResponseDto)
            .verifyComplete();

    verify(service).findById(1L);
  }
}
