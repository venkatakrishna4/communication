package com.krish.communication.exception;

import java.util.List;
import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
  private final String message;
  private final List<String> subErrors;

  public UserNotFoundException(final String message, List<String> subErrors) {
    this.message = message;
    this.subErrors = subErrors;
  }
}
