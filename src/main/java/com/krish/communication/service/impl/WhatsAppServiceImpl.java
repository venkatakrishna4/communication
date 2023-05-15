package com.krish.communication.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krish.communication.datamodel.record.CommunicationOptionsRecord;
import com.krish.communication.service.WhatsAppService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@NoArgsConstructor
public class WhatsAppServiceImpl implements WhatsAppService {

  @Value(value = "communication.whatsapp.account-sid: ACe5e6855c9fd77a5af0a3ef6895778be0")
  private String accountSid;

  @Value(value = "communication.whatsapp.auth-token: 590bd476c13092b7cf36b8261cc8da66")
  private String authToken;

  private final String TWILIO_URL =
      "https://api.twilio.com/2010-04-01/Accounts/" + accountSid + "/Messages.json";
  private static final Logger LOGGER = LoggerFactory.getLogger(WhatsAppServiceImpl.class);

  private RestTemplate restTemplate;
  private ObjectMapper objectMapper;

  @Async
  @Override
  public void sendCommunicationMessage(CommunicationOptionsRecord communicationOptionsRecord) {
    LOGGER.debug("WhatsApp Messaging Service Initiated");

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    httpHeaders.setBasicAuth(accountSid, authToken);

    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("From", "whatsapp:" + "+14155238886");
    map.add("To", "whatsapp:" + "+918125228143");
    map.add("Body", "Hello from Twilio!");

    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, httpHeaders);

    String response = restTemplate.postForObject(TWILIO_URL, entity, String.class);
    LOGGER.debug(response);
  }
}
