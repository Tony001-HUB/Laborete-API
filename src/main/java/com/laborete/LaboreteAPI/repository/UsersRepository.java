package com.laborete.LaboreteAPI.repository;

import com.laborete.LaboreteAPI.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity getUserById(UUID id);
}
