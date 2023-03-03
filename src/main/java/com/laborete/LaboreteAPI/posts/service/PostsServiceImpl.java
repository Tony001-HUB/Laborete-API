package com.laborete.LaboreteAPI.posts.service;

import com.laborete.LaboreteAPI.exception.ResourceBadRequestException;
import com.laborete.LaboreteAPI.exception.ResourceNotFoundException;
import com.laborete.LaboreteAPI.posts.entity.PostEntity;
import com.laborete.LaboreteAPI.posts.mappers.PostMapper;
import com.laborete.LaboreteAPI.posts.models.CreatePostDTO;
import com.laborete.LaboreteAPI.posts.models.FilterDTO;
import com.laborete.LaboreteAPI.posts.models.PostDTO;
import com.laborete.LaboreteAPI.posts.repository.PostRepository;
import com.laborete.LaboreteAPI.profile.entity.UserEntity;
import com.laborete.LaboreteAPI.profile.mappers.UserMapper;
import com.laborete.LaboreteAPI.profile.repository.UsersRepository;
import com.laborete.LaboreteAPI.profile.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PostsServiceImpl implements PostsService {

    private static final String USER_NOT_FOUND = "User was not found with id:";
    private static final String POST_TEXT_IS_REQUIRED = "Post cannot be without text";
    private static final String USER_CREATOR_IS_REQUIRED = "Post cannot be posted without User";
    private static final String UUID_REQUIRED = "Can`t find post without ID";
    private static final String SEARCH_FIELD_IS_EMPTY = "Search field is empty";
    private static final String POST_NOT_FOUND = "Post was not found with id:";
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;


    public List<PostDTO> getAllPosts() {
        List<PostEntity> postEntities = postRepository.findAll();
        return postMapper.postEntitiesListToPostDTOList(postEntities);
    }

    @Transactional
    public PostDTO createPost(CreatePostDTO post) {
        if (post.getText() == null || post.getText().isEmpty()) {
            throw new ResourceBadRequestException(POST_TEXT_IS_REQUIRED);
        }
        if (post.getUserId() == null) {
            throw new ResourceBadRequestException(USER_CREATOR_IS_REQUIRED);
        }

        UserEntity user = usersRepository.getUserById(post.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User was not found with id:" + post.getUserId()));
        PostEntity postEntity = postMapper.createPostDTOToPostEntity(post);
        postEntity.setCreationDate(LocalDateTime.now());
        postEntity.setUser(user);
        postRepository.save(postEntity);
        return postMapper.postEntityToPostDTO(postEntity);
    }


    public PostDTO getPostById(UUID id) {
        if (id == null) {
            throw new ResourceBadRequestException("Can`t find post without ID");
        }
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post was not found with id:" + id));
        return postMapper.postEntityToPostDTO(postEntity);

    }

    public List<PostDTO> filterPosts(FilterDTO filter) {
        var searchValue = filter.getSearchValue().trim();
        List<PostEntity> postEntities = postRepository.findByTextContainingIgnoreCase(searchValue);
        return postMapper.postEntitiesListToPostDTOList(postEntities);
    }
}

