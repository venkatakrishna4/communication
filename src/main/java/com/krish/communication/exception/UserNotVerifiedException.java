package com.krish.communication.exception;

import java.util.List;
import lombok.Getter;

@Getter
public class UserNotVerifiedException extends RuntimeException {
  private final String message;
  private final List<String> subErrors;

  public UserNotVerifiedException(final String message, final List<String> subErrors) {
    this.message = message;
    this.subErrors = subErrors;
  }
}
