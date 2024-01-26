package com.crux.society.services;

import com.crux.society.mappers.ProfileMapper;
import com.crux.society.models.ProfileResponseDto;
import com.crux.society.models.RegisterProfileDto;
import com.crux.society.models.entities.Profile;
import com.crux.society.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

  private final ProfileRepository repository;
  private final ProfileMapper mapper;

  public ProfileResponseDto registerProfile(RegisterProfileDto dto) {
    var profile = mapper.toProfile(dto);
    Profile savedProfile = repository.save(profile);
    return mapper.toProfileResponseDto(savedProfile);
  }

  public ProfileResponseDto findById(Long id) {
    return repository.findById(id).map(mapper::toProfileResponseDto)
            .orElseThrow();
  }
}
