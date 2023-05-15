package com.krish.communication.service.impl;

import com.krish.communication.datamodel.record.CommunicationOptionsRecord;
import com.krish.communication.service.InstagramService;
import org.springframework.scheduling.annotation.Async;

public class InstagramServiceImpl implements InstagramService {
  @Async
  @Override
  public void sendCommunicationMessage(CommunicationOptionsRecord communicationOptionsRecord) {}
}
