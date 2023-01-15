package com.laborete.LaboreteAPI.posts.repository;

import com.laborete.LaboreteAPI.posts.entity.PostEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {
}