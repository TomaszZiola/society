package com.crux.society.mappers;

import com.crux.society.models.RegisterProfileDto;
import com.crux.society.models.ProfileResponseDto;
import com.crux.society.models.entities.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

  public Profile toProfile(RegisterProfileDto dto) {
    return new Profile(null, dto.name(), dto.secondName());
  }

  public ProfileResponseDto toProfileResponseDto(Profile profile) {
    return new ProfileResponseDto(profile.getName(), profile.getSecondName());
  }
}
