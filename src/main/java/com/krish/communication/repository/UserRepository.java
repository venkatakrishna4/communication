package com.krish.communication.repository;

import com.krish.communication.datamodel.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {}
