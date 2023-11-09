package com.lolz.blog.service;

import com.lolz.blog.payload.PostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface PostService {

  PostDto createPost(PostDto postDto);

  List<PostDto> getAllPosts();

  PostDto getPostById(long id);

  PostDto updatePost(PostDto postDto, long id);

  void deletePost(long id);
}
