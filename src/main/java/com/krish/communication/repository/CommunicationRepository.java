package com.krish.communication.repository;

import com.krish.communication.datamodel.Communication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunicationRepository extends JpaRepository<Communication, String> {}
