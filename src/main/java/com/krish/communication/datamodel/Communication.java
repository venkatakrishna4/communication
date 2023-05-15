package com.krish.communication.datamodel;

import com.krish.communication.service.Communication.ServiceName;
import com.krish.communication.service.Communication.ServiceType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Communication {
  @Id private String id;

  @Column
  @NotBlank(message = "User ID is required")
  private String userId;

  @Column
  @NotBlank(message = "Service name is required")
  private ServiceName serviceName;

  @Column
  @NotBlank(message = "Service type is required")
  private ServiceType serviceType;

  @Column private String fromId;
  @OneToMany private List<DestinationDetails> destinationDetails;
}
