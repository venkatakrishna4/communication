package com.krish.communication.exception;

public class CommunicationNotFoundException extends RuntimeException {
  private final String message;

  public CommunicationNotFoundException(final String message) {
    this.message = message;
  }
}
