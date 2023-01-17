package com.laborete.LaboreteAPI.posts.service;

import com.laborete.LaboreteAPI.posts.models.CreatePostDTO;
import com.laborete.LaboreteAPI.posts.models.PostDTO;
import java.util.List;
import java.util.UUID;

public interface PostsService {
    List<PostDTO> getAllPosts();

    PostDTO createPost(CreatePostDTO post);

    PostDTO getPostById(UUID id);
}

