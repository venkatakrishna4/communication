package com.krish.communication.service;

import com.krish.communication.datamodel.record.CommunicationOptionsRecord;

public interface Communication {

  enum ServiceName {
    WHATSAPP,
    INSTAGRAM
  }

  enum ServiceType {
    AUTOMATED,
    MANUAL,
    TESTING
  }

  public void sendCommunicationMessage(CommunicationOptionsRecord communicationOptionsRecord);
}
