package com.krish.communication;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class TestWhatsAppMessaging {
  public static void main(String[] args) {
    String accountSid = "ACe5e6855c9fd77a5af0a3ef6895778be0";
    String token = "590bd476c13092b7cf36b8261cc8da66";
    String twilioUrl =
        "https://api.twilio.com/2010-04-01/Accounts/" + accountSid + "/Messages.json";

    // Create the RestTemplate
    RestTemplate restTemplate = new RestTemplate();

    // Set the request headers
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setBasicAuth(accountSid, token);

    // Set the request parameters
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("From", "whatsapp:" + "+14155238886");
    params.add("To", "whatsapp:" + "+918125228143");
    params.add("Body", "Hello from Twilio!");

    // Create the HttpEntity with headers and parameters
    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

    // Send the POST request to Twilio API
    ResponseEntity<String> response =
        restTemplate.exchange(twilioUrl, HttpMethod.POST, entity, String.class);

    // Check the response
    if (response.getStatusCode().is2xxSuccessful()) {
      System.out.println("WhatsApp message sent successfully!");
    } else {
      System.out.println("Failed to send WhatsApp message. Response: " + response.getBody());
    }
  }
}
