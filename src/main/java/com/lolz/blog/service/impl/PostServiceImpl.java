package com.lolz.blog.service.impl;

import com.lolz.blog.entity.Post;
import com.lolz.blog.exception.ResourceNotFoundException;
import com.lolz.blog.payload.PostDto;
import com.lolz.blog.payload.PostResponse;
import com.lolz.blog.repository.PostRepository;
import com.lolz.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;



import java.util.List;
import java.util.stream.Collectors;

@Service

public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;

  public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }


  //create post
  @Override
  public PostDto createPost(PostDto postDto) {
    //create post entity
    Post post = mapToEntity(postDto);
    //save post to database
    Post newPost = postRepository.save(post);
    //return postDto
    PostDto postResponse = mapToDTO(newPost);
    return postResponse;
  }




  @Override
  public PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir){
      Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

    Pageable pageable = PageRequest.of(pageNo, pageSize,sort);

    Page<Post> posts = postRepository.findAll(pageable);

    List<Post> listOfPosts = posts.getContent();

    List<PostDto> content = listOfPosts.stream().map(this::mapToDTO).collect(Collectors.toList());

    PostResponse postResponse = new PostResponse();
    postResponse.setContent(content);
    postResponse.setPageNo(posts.getNumber());
    postResponse.setPageSize(posts.getSize());
    postResponse.setTotalElements(posts.getTotalElements());
    postResponse.setTotalPages(posts.getTotalPages());
    postResponse.setLast(posts.isLast());

    return postResponse;

  }


  //get all posts


  //get post by id
  @Override
  public PostDto getPostById(long id) {
    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    return mapToDTO(post);
  }


  //update post
  @Override
  public PostDto updatePost(PostDto postDto, long id) {
    //get post by id
    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postDto.getId()));

    //update post
    post.setTitle(postDto.getTitle());
    post.setDescription(postDto.getDescription());
    post.setContent(postDto.getContent());

    //save post to database
    Post updatedPost = postRepository.save(post);

    //return postDto

    return mapToDTO(updatedPost);
  }


  //delete post
  @Override
  @DeleteMapping("/{id}")
  public void deletePost(@PathVariable(name = "id") long id) {
    //get post by id
    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    //delete post
    postRepository.delete(post);

  }


  private PostDto mapToDTO(Post post) {
    PostDto postDto = new PostDto();
    postDto.setId(post.getId());
    postDto.setTitle(post.getTitle());
    postDto.setDescription(post.getDescription());
    postDto.setContent(post.getContent());
    return postDto;
  }

  private Post mapToEntity(PostDto postDto) {
    Post post = new Post();
    post.setId(postDto.getId());
    post.setTitle(postDto.getTitle());
    post.setDescription(postDto.getDescription());
    post.setContent(postDto.getContent());
    return post;
  }

}
