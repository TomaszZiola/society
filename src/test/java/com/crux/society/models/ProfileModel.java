package com.crux.society.models;

import com.crux.society.models.entities.Address;
import com.crux.society.models.entities.Profile;

public final class ProfileModel {

  public static Profile unpersisted() {
    Profile profile = new Profile(null, "Tomasz", "Zioła", null);
    profile.setAddress(new Address(null, "Poland", "Katowice", "Orkana", "40-553", profile));
    return profile;
  }

  public static Profile basic() {
    Profile profile = new Profile(1L, "Tomasz", "Zioła", null);
    profile.setAddress(new Address(1L, "Poland", "Katowice", "Orkana", "40-553", profile));
    return profile;
  }
}
