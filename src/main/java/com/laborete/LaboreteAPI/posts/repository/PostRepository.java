package com.laborete.LaboreteAPI.posts.repository;

import com.laborete.LaboreteAPI.posts.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;


public interface PostRepository extends JpaRepository<PostEntity, UUID>, JpaSpecificationExecutor<PostEntity> {
}