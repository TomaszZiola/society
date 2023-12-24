package com.crux.society.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.crux.society.controllers.ProfileController;
import com.crux.society.mappers.ProfileMapper;
import com.crux.society.models.ProfileModel;
import com.crux.society.models.ProfileResponseDto;
import com.crux.society.models.ProfileResponseDtoModel;
import com.crux.society.models.RegisterProfileDto;
import com.crux.society.models.RegisterProfileDtoModel;
import com.crux.society.models.entities.Profile;
import com.crux.society.repositories.ProfileRepository;
import com.crux.society.services.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import reactor.core.publisher.Mono;

@SuppressWarnings("checkstyle:VisibilityModifier")
public abstract class BaseUnitTest {

  protected final ProfileMapper mapper = mock(ProfileMapper.class);
  protected final ProfileRepository repository = mock(ProfileRepository.class);
  protected final ProfileService service = mock(ProfileService.class);

  protected Profile profile;
  protected RegisterProfileDto registerProfileDto;
  protected ProfileResponseDto profileResponseDto;

  protected ProfileController controller = new ProfileController(service);
  protected ProfileMapper mapperImpl = new ProfileMapper();
  protected ProfileService serviceImpl = new ProfileService(repository, mapper);

  @BeforeEach
  public final void init() {
    profile = ProfileModel.basic();
    registerProfileDto = RegisterProfileDtoModel.basic();
    profileResponseDto = ProfileResponseDtoModel.basic();

    when(mapper.toProfile(registerProfileDto)).thenReturn(profile);
    when(mapper.toProfileResponseDto(profile)).thenReturn(profileResponseDto);
    when(repository.findById(1L)).thenReturn(Mono.just(profile));
    when(repository.save(profile)).thenReturn(Mono.just(profile));
    when(service.registerProfile(registerProfileDto))
        .thenReturn(Mono.just(profileResponseDto));
    when(service.findById(1L))
        .thenReturn(Mono.just(profileResponseDto));
  }
}
