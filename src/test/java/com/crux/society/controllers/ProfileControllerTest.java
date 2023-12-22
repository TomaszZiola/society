package com.crux.society.controllers;

import static org.mockito.Mockito.verify;
import static reactor.test.StepVerifier.create;

import com.crux.society.utils.BaseUnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfileControllerTest extends BaseUnitTest {

  @Test
  @DisplayName("ProfileController#registerProfile should return RegisterProfileResponseDto")
  public void registerProfile() {
    create(controller.registerProfile(registerProfileDto))
        .expectNext(registerProfileResponseDto)
        .verifyComplete();

    verify(service).registerProfile(registerProfileDto);
  }
}
