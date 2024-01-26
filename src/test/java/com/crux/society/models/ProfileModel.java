package com.crux.society.models;


import com.crux.society.models.entities.Profile;

public final class ProfileModel {

  public static Profile basic() {
    return new Profile(null, "Tomasz", "Zioła");
  }
}
