package com.lolz.blog.controller;


import com.lolz.blog.payload.PostDto;
import com.lolz.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {


  final private PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  //get all posts
  @GetMapping
  public List<PostDto> getAllPosts() {
    return postService.getAllPosts();
  }

  //get post by id
  @GetMapping("/{id}")
  public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(postService.getPostById(id));
  }


  //create post
  @PostMapping
  public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
    return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

  }

  //update post
  @PutMapping("/{id}")
  public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
    PostDto postResponse = postService.updatePost(postDto, id);
    return new ResponseEntity<>(postResponse, HttpStatus.OK);
  }


  //delete post
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletePost(@PathVariable(name = "id") long id) {
    postService.deletePost(id);
    return new ResponseEntity<>("Post successfully deleted", HttpStatus.OK);
  }


}
