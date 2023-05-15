package com.krish.communication.service.impl;

import com.krish.communication.datamodel.User;
import com.krish.communication.datamodel.record.UserRequestRecord;
import com.krish.communication.datamodel.record.UserResponseRecord;
import com.krish.communication.exception.UserNotFoundException;
import com.krish.communication.exception.UserNotVerifiedException;
import com.krish.communication.repository.UserRepository;
import com.krish.communication.service.JsonParseService;
import com.krish.communication.service.UserService;
import com.krish.communication.service.UserVerificationService;
import com.krish.communication.utils.UserUtils;
import com.krish.communication.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final JsonParseService jsonParseService;
  private final UserVerificationService userVerificationService;
  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

  @Override
  public UserResponseRecord saveUser(UserRequestRecord userRequestRecord) {
    if (isValidUserDetails(userRequestRecord.phone(), userRequestRecord.email())) {
      User user =
          User.builder()
              .id(Utils.generateUUID())
              .name(StringUtils.trim(userRequestRecord.name()))
              .email(StringUtils.trim(userRequestRecord.email().toLowerCase()))
              .password(userRequestRecord.password())
              .communicationList(userRequestRecord.communicationList())
              .build();

      userRepository.save(user);
      return UserUtils.toUserResponseRecord(user);
    } else {
      throw new UserNotVerifiedException(
          "User does not verified successfully",
          List.of("Please verify your phone number", "Please verify your email address"));
    }
  }

  @Override
  public UserResponseRecord getUser(String id) {
    Optional<User> user = userRepository.findById(id);
    return user.map(UserUtils::toUserResponseRecord)
        .orElseThrow(
            () ->
                new UserNotFoundException(
                    "User not found", List.of("User " + id + " not found in the system")));
  }

  @Override
  public List<UserResponseRecord> getUsers(Pageable pageable) {
    List<UserResponseRecord> users = new ArrayList<>();
    userRepository
        .findAll(pageable)
        .forEach(user -> users.add(UserUtils.toUserResponseRecord(user)));
    return users;
  }

  @Override
  public UserResponseRecord updateUser(UserRequestRecord userRequestRecord) {
    Optional<User> existingUser = userRepository.findById(userRequestRecord.id());
    return existingUser
        .map(
            user -> {
              User updateUser = existingUser.get();
              updateUser.setName(StringUtils.trim(userRequestRecord.name()));
              updateUser.setEmail(StringUtils.trim(userRequestRecord.email().toLowerCase()));
              updateUser.setPassword(userRequestRecord.password());
              updateUser.setCommunicationList(userRequestRecord.communicationList());
              userRepository.save(updateUser);
              return UserUtils.toUserResponseRecord(updateUser);
            })
        .orElseThrow(
            () ->
                new UserNotFoundException(
                    "User not found",
                    List.of("User " + userRequestRecord.id() + " not found in the system")));
  }

  @Override
  public void deleteUser(String id) {
    Optional<User> existingUser = userRepository.findById(id);
    existingUser.ifPresent(user -> userRepository.deleteById(user.getId()));
  }

  private boolean isValidUserDetails(String phone, String email) {
    List<String> validPhoneNumbers = jsonParseService.parseValidPhoneNumbersJson();
    boolean isValidPhone = false, isValidEmail = false;
    if (!validPhoneNumbers.contains(phone)) {
      isValidPhone = userVerificationService.isValidPhoneNumber();
    }
    List<String> validEmails = jsonParseService.parseValidEmailsJson();
    if (!validEmails.contains(email)) {
      isValidEmail = userVerificationService.isValidEmailAddress();
    }
    return isValidPhone && isValidEmail;
  }
}
