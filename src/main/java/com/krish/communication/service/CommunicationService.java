package com.krish.communication.service;

import com.krish.communication.datamodel.Communication;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CommunicationService {
  void addService(Communication communication);

  Communication getService(String id);

  List<Communication> getServices(Pageable pageable);

  void updateService(Communication communication);

  Communication deleteService(String id);
}
