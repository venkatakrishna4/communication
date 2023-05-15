package com.krish.communication.datamodel;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class DestinationDetails {
  @Id private String id;
  @Column private String toId;
  @Column private String message;
  @ElementCollection private List<Long> durations = new ArrayList<>();
}
