package com.laborete.LaboreteAPI.posts.models.mappers;

import com.laborete.LaboreteAPI.posts.entity.PostEntity;
import com.laborete.LaboreteAPI.posts.models.CreatePostDTO;
import com.laborete.LaboreteAPI.posts.models.PostDTO;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface PostMapper {
    PostEntity createPostDTOToPostEntity(CreatePostDTO post);

    PostDTO postEntityToPostDTO(PostEntity post);

    PostEntity postDTOToPostEntity(PostDTO post);
}