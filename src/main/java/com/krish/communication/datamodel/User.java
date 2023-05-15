package com.krish.communication.datamodel;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable, Cloneable {
  @Id @Column private String id;
  @Column private String name;
  @Column private String email;
  @Column private String password;
  @Column private String phone;
  @OneToMany private List<Communication> communicationList;

  @Override
  public User clone() {
    try {
      // TODO: copy mutable state here, so the clone can't change the internals of the original
      return (User) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }
}
