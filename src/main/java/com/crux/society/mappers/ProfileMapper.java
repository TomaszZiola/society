package com.crux.society.mappers;

import com.crux.society.models.ProfileResponseDto;
import com.crux.society.models.RegisterProfileDto;
import com.crux.society.models.entities.Address;
import com.crux.society.models.entities.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

  public Profile toProfile(RegisterProfileDto dto) {
    Profile profile = new Profile(null, dto.name(), dto.secondName(), null);
    profile.setAddress(toAddress(dto, profile));
    return profile;
  }

  private Address toAddress(RegisterProfileDto dto, Profile profile) {
    return new Address(null, dto.country(), dto.city(), dto.street(), dto.postalCode(), profile);
  }

  public ProfileResponseDto toProfileResponseDto(Profile profile) {
    return new ProfileResponseDto(
        profile.getName(),
        profile.getSecondName(),
        profile.getAddress().getCountry(),
        profile.getAddress().getCity(),
        profile.getAddress().getStreet(),
        profile.getAddress().getPostalCode());
  }
}
