package com.crux.society.models;

import jakarta.annotation.Nonnull;

public record RegisterProfileDto(
    @Nonnull String name,
    @Nonnull String secondName,
    String country,
    String city,
    String street,
    String postalCode) {

}
