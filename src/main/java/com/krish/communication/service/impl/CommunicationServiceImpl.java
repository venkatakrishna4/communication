package com.krish.communication.service.impl;

import com.krish.communication.datamodel.Communication;
import com.krish.communication.datamodel.User;
import com.krish.communication.exception.CommunicationNotFoundException;
import com.krish.communication.repository.CommunicationRepository;
import com.krish.communication.repository.UserRepository;
import com.krish.communication.service.CommunicationService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
public class CommunicationServiceImpl implements CommunicationService {
  private final UserRepository userRepository;
  private final CommunicationRepository communicationRepository;

  @Override
  public void addService(Communication communication) {
    Optional<User> existingUser = userRepository.findById(communication.getUserId());
    existingUser.ifPresent(
        user -> {
          if (Objects.isNull(user.getCommunicationList())) {
            user.setCommunicationList(new ArrayList<>());
          }
          user.getCommunicationList().add(communication);
          userRepository.save(existingUser.get());

          Communication newCommunication =
              Communication.builder()
                  .serviceType(communication.getServiceType())
                  .serviceName(communication.getServiceName())
                  .fromId(user.getPhone())
                  .destinationDetails(communication.getDestinationDetails())
                  .userId(communication.getUserId())
                  .build();
          communicationRepository.save(newCommunication);
        });
  }

  @Override
  public Communication getService(String id) {
    return communicationRepository
        .findById(id)
        .orElseThrow(() -> new CommunicationNotFoundException("Communication not found"));
  }

  @Override
  public List<Communication> getServices(Pageable pageable) {
    return communicationRepository.findAll(pageable).stream().toList();
  }

  @Override
  public void updateService(Communication communication) {}

  @Override
  public Communication deleteService(String id) {
    return null;
  }
}
