package com.krish.communication.controller;

import com.krish.communication.datamodel.record.CommunicationOptionsRecord;
import com.krish.communication.service.WhatsAppService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/whatsapp")
@AllArgsConstructor
public class WhatsAppController {

  private WhatsAppService whatsAppService;

  @PostMapping("/manual")
  public @ResponseBody ResponseEntity<String> sendManualMessage(
      @RequestBody CommunicationOptionsRecord communicationOptionsRecord) {
    whatsAppService.sendCommunicationMessage(communicationOptionsRecord);
    return ResponseEntity.ok("WhatsApp message has been sent successfully");
  }
}
