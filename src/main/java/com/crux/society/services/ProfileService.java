package com.crux.society.services;

import com.crux.society.mappers.ProfileMapper;
import com.crux.society.models.RegisterProfileDto;
import com.crux.society.models.ProfileResponseDto;
import com.crux.society.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProfileService {

  private final ProfileRepository repository;
  private final ProfileMapper mapper;

  public Mono<ProfileResponseDto> registerProfile(RegisterProfileDto dto) {
    var profile = mapper.toProfile(dto);
    return repository.save(profile).map(mapper::toProfileResponseDto);
  }

  public Mono<ProfileResponseDto> findById(Long id) {
    return repository.findById(id).map(mapper::toProfileResponseDto);
  }
}
