package com.krish.communication.service.impl;

import com.krish.communication.service.UserVerificationService;

public class UserVerificationServiceImpl implements UserVerificationService {
  @Override
  public boolean isValidPhoneNumber() {
    return false;
  }

  @Override
  public boolean isValidEmailAddress() {
    return false;
  }
}
