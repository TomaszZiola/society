package com.crux.society.controllers;

import com.crux.society.models.ProfileResponseDto;
import com.crux.society.models.RegisterProfileDto;
import com.crux.society.services.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/society")
public class ProfileController {

  private final ProfileService service;

  @PostMapping("/register")
  public ProfileResponseDto registerProfile(@Valid @RequestBody RegisterProfileDto dto) {
    return service.registerProfile(dto);
  }

  @GetMapping("/{id}")
  public ProfileResponseDto getProfile(@PathVariable Long id) {
    return service.findById(id);
  }
}
