package com.crux.society.controllers;

import com.crux.society.models.RegisterProfileDto;
import com.crux.society.models.RegisterProfileResponseDto;
import com.crux.society.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/society")
@RequiredArgsConstructor
public class ProfileController {

  private final ProfileService service;

  @PostMapping("/register")
  public Mono<RegisterProfileResponseDto> registerProfile(@RequestBody RegisterProfileDto dto) {
    return service.registerProfile(dto);
  }
}
