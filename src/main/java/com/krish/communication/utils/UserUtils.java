package com.krish.communication.utils;

import com.krish.communication.datamodel.User;
import com.krish.communication.datamodel.record.UserResponseRecord;

public class UserUtils {
  private UserUtils() {}

  public static UserResponseRecord toUserResponseRecord(User user) {
    return new UserResponseRecord(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getPhone(),
        user.getCommunicationList());
  }
}
