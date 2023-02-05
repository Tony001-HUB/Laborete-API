package com.laborete.LaboreteAPI.profile.repository;

import com.laborete.LaboreteAPI.profile.entity.UserAvatarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserAvatarRepository extends JpaRepository<UserAvatarEntity, UUID> {
    Optional<UserAvatarEntity> getUserAvatarById(UUID id);
}