package com.krish.communication.datamodel;

import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {
  private String id;
  private String message;
  private List<String> subErrors;
  private final Date timeStamp = new Date();
}
