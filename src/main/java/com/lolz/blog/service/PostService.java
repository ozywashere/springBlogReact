package com.lolz.blog.service;

import com.lolz.blog.payload.PostDto;
import com.lolz.blog.payload.PostResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface PostService {

  PostDto createPost(PostDto postDto);

  PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

  PostDto getPostById(long id);

  PostDto updatePost(PostDto postDto, long id);

  void deletePost(long id);
}
