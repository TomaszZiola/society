package com.crux.society.models.entities;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

  @Id
  @SequenceGenerator(name = "profile_id_seq", sequenceName = "profile_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = SEQUENCE, generator = "profile_id_seq")
  private Long id;
  private String name;
  private String secondName;

  @OneToOne(mappedBy = "profile", cascade = PERSIST, fetch = LAZY)
  private Address address;

  @Override
  public String toString() {
    return "Profile{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", secondName='"
        + secondName
        + '\''
        + '}';
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Profile other = (Profile) obj;
    return id != null && id.equals(other.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, secondName);
  }
}
