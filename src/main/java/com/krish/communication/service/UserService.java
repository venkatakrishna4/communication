package com.krish.communication.service;

import com.krish.communication.datamodel.record.UserRequestRecord;
import com.krish.communication.datamodel.record.UserResponseRecord;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
  UserResponseRecord saveUser(UserRequestRecord userRequestRecord);

  UserResponseRecord getUser(String id);

  List<UserResponseRecord> getUsers(Pageable pageable);

  UserResponseRecord updateUser(UserRequestRecord userRequestRecord);

  void deleteUser(String id);
}
