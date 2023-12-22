package com.crux.society.services;

import static org.mockito.Mockito.verify;
import static reactor.test.StepVerifier.create;

import com.crux.society.utils.BaseUnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfileServiceTest extends BaseUnitTest {

  @Test
  @DisplayName("ProfileService#registerProfile should return RegisterProfileResponseDto")
  public void serviceTest() {

    var result = serviceImpl.registerProfile(registerProfileDto);

    create(result)
        .expectNextMatches(response -> response.equals(registerProfileResponseDto))
        .verifyComplete();

    verify(mapper).toProfile(registerProfileDto);
    verify(mapper).toProfileResponseDto(profile);
    verify(repository).save(profile);
  }
}
