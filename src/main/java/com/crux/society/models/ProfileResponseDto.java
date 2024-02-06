package com.crux.society.models;

public record ProfileResponseDto(
    String name,
    String secondName,
    String country,
    String city,
    String street,
    String postalCode) {

}
