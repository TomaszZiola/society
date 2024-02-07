package com.crux.society.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorDetails(@JsonProperty("message") String message) {

}
