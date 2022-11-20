package com.laborete.LaboreteAPI.repository;

import com.laborete.LaboreteAPI.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users getUsersById(UUID id);
}
