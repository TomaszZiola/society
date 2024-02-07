package com.crux.society.exceptions;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.crux.society.models.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionMapper {

  @ExceptionHandler(ProfileNotFoundException.class)
  public ResponseEntity<Object> handleProfileNotFoundException(ProfileNotFoundException ex) {
    var body = new ErrorDetails(ex.getLocalizedMessage());
    return new ResponseEntity<>(body, NOT_FOUND);
  }
}
