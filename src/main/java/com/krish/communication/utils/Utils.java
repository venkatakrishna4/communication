package com.krish.communication.utils;

import java.util.UUID;

public final class Utils {
  private Utils() {}

  public static String generateUUID() {
    return UUID.randomUUID().toString();
  }
}
