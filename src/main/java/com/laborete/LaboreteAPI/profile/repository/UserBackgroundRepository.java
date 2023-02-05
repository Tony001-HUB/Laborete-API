package com.laborete.LaboreteAPI.profile.repository;

import com.laborete.LaboreteAPI.profile.entity.UserBackgroundEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserBackgroundRepository extends JpaRepository<UserBackgroundEntity, UUID> {

    Optional<UserBackgroundEntity> getUserBackgroundById(UUID id);
}
