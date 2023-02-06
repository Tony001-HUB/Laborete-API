package com.laborete.LaboreteAPI.posts.repository;

import com.laborete.LaboreteAPI.posts.entity.PostEntity;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {

    List<PostEntity> findByTextContainingIgnoreCase(String text);
}