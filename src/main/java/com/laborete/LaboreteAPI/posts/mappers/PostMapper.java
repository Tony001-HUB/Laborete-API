package com.laborete.LaboreteAPI.posts.mappers;

import com.laborete.LaboreteAPI.posts.entity.PostEntity;
import com.laborete.LaboreteAPI.posts.models.AuthorDTO;
import com.laborete.LaboreteAPI.posts.models.CreatePostDTO;
import com.laborete.LaboreteAPI.posts.models.PostDTO;
import com.laborete.LaboreteAPI.profile.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface PostMapper {
    PostEntity createPostDTOToPostEntity(CreatePostDTO post);

    @Mapping(target = "authorDTO", source = "user")
    PostDTO postEntityToPostDTO(PostEntity post);

    PostEntity postDTOToPostEntity(PostDTO post);

    AuthorDTO userEntityToAuthorDTO(UserEntity user);

    List<PostDTO> postEntitiesListToPostDTOList(List<PostEntity> postEntities);
}