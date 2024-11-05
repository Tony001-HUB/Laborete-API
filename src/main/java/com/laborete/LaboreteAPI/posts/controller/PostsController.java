package com.laborete.LaboreteAPI.posts.controller;

import com.laborete.LaboreteAPI.posts.models.CreatePostDTO;
import com.laborete.LaboreteAPI.posts.models.PostDTO;
import com.laborete.LaboreteAPI.posts.service.PostsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @ApiOperation("Get Posts by Anything")
    @PostMapping("/filter")
    private ResponseEntity<List<PostDTO>> rsqlFilterPosts(String rsqlFilter) {
        return new ResponseEntity<>(postsService.filterPosts(rsqlFilter), HttpStatus.OK);
    }
}

