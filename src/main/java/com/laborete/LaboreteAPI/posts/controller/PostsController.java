package com.laborete.LaboreteAPI.posts.controller;

import com.laborete.LaboreteAPI.posts.models.CreatePostDTO;
import com.laborete.LaboreteAPI.posts.models.FilterDTO;
import com.laborete.LaboreteAPI.posts.models.PostDTO;
import com.laborete.LaboreteAPI.posts.service.PostsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"v1/posts"})
@Api(
        value = "Posts",
        produces = "application/json",
        tags = {"Posts"},
        description = "Posts Controller"
)
public class PostsController {
    @Autowired
    private PostsService postsService;

    @ApiOperation("Get all posts")
    @GetMapping({""})
    private List<PostDTO> getAllPosts() {
        return this.postsService.getAllPosts();
    }

    @ApiOperation("Create new post")
    @PostMapping({""})
    private ResponseEntity<PostDTO> createPost(@RequestBody CreatePostDTO post) {
        return new ResponseEntity<>(this.postsService.createPost(post), HttpStatus.CREATED);
    }

    @ApiOperation("Get post by id")
    @GetMapping({"/{id}"})
    private ResponseEntity<PostDTO> getPostById(@PathVariable UUID id) {
        return new ResponseEntity<>(this.postsService.getPostById(id), HttpStatus.OK);
    }

    @ApiOperation("Get posts filtered by searchValue")
    @PostMapping({"/filter"})
    private List<PostDTO> filterPosts(@RequestBody FilterDTO searchValue) {
        return this.postsService.filterPosts(searchValue);
    }
}

