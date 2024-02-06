package com.crux.society.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import com.crux.society.utils.BaseUnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfileServiceTest extends BaseUnitTest {

  @Test
  @DisplayName("ProfileService#registerProfile should return ProfileResponseDto")
  public void registerProfileTest() {
    // when
    var result = serviceImpl.registerProfile(registerProfileDto);

    // then
    assertThat(result).isEqualTo(profileResponseDto);

    verify(profileMapper).toProfile(registerProfileDto);
    verify(profileMapper).toProfileResponseDto(profile);
    verify(repository).save(profile);
  }

  @Test
  @DisplayName("ProfileService#findById should return ProfileResponseDto")
  public void findByIdTest() {
    // whenw
    var result = serviceImpl.findById(1L);

    // then
    assertThat(result).isEqualTo(profileResponseDto);
    verify(profileMapper).toProfileResponseDto(profile);
    verify(repository).findById(1L);
  }
}
