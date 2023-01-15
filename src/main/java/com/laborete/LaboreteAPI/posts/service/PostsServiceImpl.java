package com.laborete.LaboreteAPI.posts.service;

import com.laborete.LaboreteAPI.posts.entity.PostEntity;
import com.laborete.LaboreteAPI.posts.models.CreatePostDTO;
import com.laborete.LaboreteAPI.posts.models.PostDTO;
import com.laborete.LaboreteAPI.posts.models.mappers.PostMapper;
import com.laborete.LaboreteAPI.posts.repository.PostRepository;
import com.laborete.LaboreteAPI.profile.entity.UserEntity;
import com.laborete.LaboreteAPI.profile.exception.ResourceBadRequestException;
import com.laborete.LaboreteAPI.profile.exception.ResourceNotFoundException;
import com.laborete.LaboreteAPI.profile.mappers.UserMapper;
import com.laborete.LaboreteAPI.profile.models.UserDTO;
import com.laborete.LaboreteAPI.profile.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostsServiceImpl implements PostsService {
    private static final String USER_NOT_FOUND = "User was not found with id:";
    private static final String POST_TEXT_IS_REQUIRED = "Post cannot be without text";
    private static final String USER_CREATOR_IS_REQUIRED = "Post cannot be posted without User";
    private static final String UUID_REQUIRED = "Can`t find post without ID";
    private static final String POST_NOT_FOUND = "Post was not found with id:";
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;

    public PostsServiceImpl() {
    }

    public List<PostDTO> getAllPosts() {
        List<PostEntity> postEntities = this.postRepository.findAll();
        List<PostDTO> postDTOList = new ArrayList();
        for (PostEntity postEntity : postEntities) {
            PostDTO postDTO = this.postMapper.postEntityToPostDTO(postEntity);
            postDTO.setUserId(postEntity.getUser().getId());
            postDTO.setUserFirstName(postEntity.getUser().getFirstName());
            postDTO.setUserSecondName(postEntity.getUser().getSecondName());
            postDTO.setUserPosition(postEntity.getUser().getPosition());
            postDTO.setUserAvatarDTO(this.usersService.getUserById(postEntity.getUser().getId()).getUserAvatarEntity());
            postDTOList.add(postDTO);
        }

        return postDTOList;
    }

    public PostDTO createPost(CreatePostDTO post) {
        if (post.getText() == null || post.getText().isEmpty()) {
            throw new ResourceBadRequestException(POST_TEXT_IS_REQUIRED);
        }
        if (post.getUserId() == null) {
            throw new ResourceBadRequestException(USER_CREATOR_IS_REQUIRED);
        }
        UserEntity userEntity = this.userMapper.userDTOToUserEntity(this.usersService.getUserById(post.getUserId()));
        UserDTO userDTO = this.usersService.getUserById(post.getUserId());
        PostEntity postEntity = new PostEntity();
        postEntity.setId(post.getId());
        postEntity.setText(post.getText());
        postEntity.setCreationDate(LocalDateTime.now());
        postEntity.setUser(userEntity);
        this.postRepository.save(postEntity);
        PostDTO postDTO = this.postMapper.postEntityToPostDTO(postEntity);
        postDTO.setUserId(userEntity.getId());
        postDTO.setUserFirstName(userEntity.getFirstName());
        postDTO.setUserSecondName(userEntity.getSecondName());
        postDTO.setUserPosition(userEntity.getPosition());
        postDTO.setUserAvatarDTO(userDTO.getUserAvatarEntity());
        return postDTO;
    }


    public PostDTO findById(UUID id) {
        if (id == null) {
            throw new ResourceBadRequestException("Can`t find post without ID");
        }
        PostEntity postEntity = (PostEntity) this.postRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Post was not found with id:" + id);
        });
        PostDTO postDTO = this.postMapper.postEntityToPostDTO(postEntity);
        postDTO.setUserId(postEntity.getUser().getId());
        postDTO.setUserFirstName(postEntity.getUser().getFirstName());
        postDTO.setUserSecondName(postEntity.getUser().getSecondName());
        postDTO.setUserPosition(postEntity.getUser().getPosition());
        postDTO.setUserAvatarDTO(this.usersService.getUserById(postEntity.getUser().getId()).getUserAvatarEntity());
        return postDTO;

    }
}

