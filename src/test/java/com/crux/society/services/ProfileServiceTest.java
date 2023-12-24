package com.crux.society.services;

import static org.mockito.Mockito.verify;
import static reactor.test.StepVerifier.create;

import com.crux.society.utils.BaseUnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfileServiceTest extends BaseUnitTest {

  @Test
  @DisplayName("ProfileService#registerProfile should return ProfileResponseDto")
  public void registerProfileTest() {
    // given
    var result = serviceImpl.registerProfile(registerProfileDto);

    // when then
    create(result)
        .expectNextMatches(response -> response.equals(profileResponseDto))
        .verifyComplete();

    verify(mapper).toProfile(registerProfileDto);
    verify(mapper).toProfileResponseDto(profile);
    verify(repository).save(profile);
  }

  @Test
  @DisplayName("ProfileService#findById should return ProfileResponseDto")
  public void findByIdTest() {
    // given
    var result = serviceImpl.findById(1L);

    // when then
    create(result)
            .expectNextMatches(response -> response.equals(profileResponseDto))
            .verifyComplete();

    verify(mapper).toProfileResponseDto(profile);
    verify(repository).findById(1L);
  }
}
