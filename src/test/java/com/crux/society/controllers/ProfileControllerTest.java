package com.crux.society.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import com.crux.society.models.ProfileResponseDto;
import com.crux.society.utils.BaseUnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfileControllerTest extends BaseUnitTest {

  @Test
  @DisplayName("ProfileController#registerProfile should return ProfileResponseDto")
  public void registerProfileTest() {
    // when
    ProfileResponseDto result = controller.registerProfile(registerProfileDto);

    // then
    assertThat(result).isEqualTo(profileResponseDto);
    verify(service).registerProfile(registerProfileDto);
  }

  @Test
  @DisplayName("ProfileController#getProfile should return ProfileResponseDto")
  public void getProfileTest() {
    // when
    ProfileResponseDto result = controller.getProfile(1L);

    // then
    assertThat(result).isEqualTo(profileResponseDto);
    verify(service).findById(1L);
  }
}
