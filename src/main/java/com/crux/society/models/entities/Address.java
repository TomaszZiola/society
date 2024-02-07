package com.crux.society.models.entities;

import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {

  @Id private Long id;
  private String country;
  private String city;
  private String street;
  private String postalCode;

  @OneToOne(fetch = LAZY)
  @MapsId
  private Profile profile;

  @Override
  public String toString() {
    return "Address{"
        + "id="
        + id
        + ", country='"
        + country
        + '\''
        + ", city='"
        + city
        + '\''
        + ", street='"
        + street
        + '\''
        + ", postalCode='"
        + postalCode
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
    Address other = (Address) obj;
    return id != null && id.equals(other.getId());
  }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, street, postalCode);
    }
}
