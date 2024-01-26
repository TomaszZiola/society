package com.crux.society.models;

import jakarta.annotation.Nonnull;
import org.springframework.web.multipart.MultipartFile;

public record RegisterProfileDto(
        @Nonnull String name,
        @Nonnull String secondName,
        MultipartFile image
) {

}
