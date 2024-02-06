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
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;

@SuppressWarnings("checkstyle:VisibilityModifier")
public abstract class BaseUnitTest {

  protected final ProfileMapper profileMapper = mock(ProfileMapper.class);
  protected final ProfileRepository repository = mock(ProfileRepository.class);
  protected final ProfileService service = mock(ProfileService.class);

  protected Profile profile;
  protected Profile unpersistedProfile;
  protected RegisterProfileDto registerProfileDto;
  protected ProfileResponseDto profileResponseDto;

  protected ProfileController controller = new ProfileController(service);
  protected ProfileMapper mapperImpl = new ProfileMapper();
  protected ProfileService serviceImpl = new ProfileService(repository, profileMapper);

  @BeforeEach
  public final void init() {
    profile = ProfileModel.basic();
    unpersistedProfile = ProfileModel.unpersisted();
    registerProfileDto = RegisterProfileDtoModel.basic();
    profileResponseDto = ProfileResponseDtoModel.basic();

    when(profileMapper.toProfile(registerProfileDto)).thenReturn(profile);
    when(profileMapper.toProfileResponseDto(profile)).thenReturn(profileResponseDto);
    when(repository.findById(1L)).thenReturn(Optional.of(profile));
    when(repository.save(profile)).thenReturn(profile);
    when(service.registerProfile(registerProfileDto)).thenReturn(profileResponseDto);
    when(service.findById(1L)).thenReturn(profileResponseDto);
  }
}
