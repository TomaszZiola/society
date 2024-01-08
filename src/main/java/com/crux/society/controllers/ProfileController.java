package com.crux.society.controllers;

import com.crux.society.models.RegisterProfileDto;
import com.crux.society.models.ProfileResponseDto;
import com.crux.society.services.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public Mono<ProfileResponseDto> registerProfile(@Valid @RequestBody RegisterProfileDto dto) {
    return servicee.registerProfile(dto);
  }

  @GetMapping("/{id}")
  public Mono<ProfileResponseDto> getProfile(@PathVariable Long id) {
    return service.findById(id);
  }
}
