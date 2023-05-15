package com.krish.communication.service;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface JsonParseService {
  List<String> parseValidPhoneNumbersJson();

  List<String> parseValidEmailsJson();
}
