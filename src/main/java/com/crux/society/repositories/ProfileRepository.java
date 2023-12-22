package com.crux.society.repositories;

import com.crux.society.models.entities.Profile;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ProfileRepository extends R2dbcRepository<Profile, Long> {

}
