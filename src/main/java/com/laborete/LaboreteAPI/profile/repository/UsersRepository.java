package com.laborete.LaboreteAPI.profile.repository;

import com.laborete.LaboreteAPI.profile.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> getUserById(UUID id);
}
