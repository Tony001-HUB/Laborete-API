package com.laborete.LaboreteAPI.repository;

import com.laborete.LaboreteAPI.entity.UserAvatarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAvatarRepository extends JpaRepository<UserAvatarEntity, UUID> { }
