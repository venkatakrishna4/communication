package com.krish.communication.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krish.communication.service.JsonParseService;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
public class JsonParseServiceImpl implements JsonParseService {
  private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();
  private static final Logger LOGGER = LoggerFactory.getLogger(JsonParseServiceImpl.class);
  private final ObjectMapper objectMapper;

  @Override
  public List<String> parseValidPhoneNumbersJson() {
    try {
      InputStream inputStream = CLASS_LOADER.getResourceAsStream("json/ValidPhoneNumbers.json");
      return objectMapper.readValue(inputStream, new TypeReference<>() {});
    } catch (IOException ex) {
      LOGGER.error(ex.getMessage(), ex);
    }
    return new ArrayList<>();
  }

  @Override
  public List<String> parseValidEmailsJson() {
    try {
      InputStream inputStream = CLASS_LOADER.getResourceAsStream("json/ValidEmailsJson.json");
      return objectMapper.readValue(inputStream, new TypeReference<>() {});
    } catch (IOException ex) {
      LOGGER.error(ex.getMessage(), ex);
    }
    return new ArrayList<>();
  }
}
