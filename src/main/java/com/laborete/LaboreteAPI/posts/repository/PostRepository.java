package com.laborete.LaboreteAPI.posts.repository;

import com.laborete.LaboreteAPI.posts.entity.PostEntity;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, UUID>, JpaSpecificationExecutor<PostEntity> {

    List<PostEntity> findByTextContainingIgnoreCase(String text);
}