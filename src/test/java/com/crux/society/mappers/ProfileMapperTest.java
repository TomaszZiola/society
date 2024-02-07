package com.crux.society.mappers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.crux.society.utils.BaseUnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfileMapperTest extends BaseUnitTest {

  @Test
  @DisplayName("ProfileMapper#toProfile should map RegisterProfileDto to Profile")
  public void toProfileTest() {
    var result = mapperImpl.toProfile(registerProfileDto);
    assertThat(result).usingRecursiveComparison().isEqualTo(unpersistedProfile);
  }

  @Test
  @DisplayName("ProfileMapper#toProfile should map Profile to RegisterProfileResponseDto")
  public void toProfileResponseDtoTest() {
    var result = mapperImpl.toProfileResponseDto(profile);
    assertThat(result).isEqualTo(profileResponseDto);
  }
}
