package com.lolz.blog.service.impl;

import com.lolz.blog.entity.Comment;
import com.lolz.blog.entity.Post;
import com.lolz.blog.exception.BlogApiException;
import com.lolz.blog.exception.ResourceNotFoundException;
import com.lolz.blog.payload.CommentDto;
import com.lolz.blog.repository.CommentRepository;
import com.lolz.blog.repository.PostRepository;
import com.lolz.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


  private final PostRepository postRepository;
  private final CommentRepository commentRepository;

  private final ModelMapper mapper;

  public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository, ModelMapper mapper) {
    this.postRepository = postRepository;
    this.commentRepository = commentRepository;
    this.mapper = mapper;
  }


  //CREATE COMMENT

  public CommentDto createComment(Long postId, CommentDto commentDto) {
    Comment comment = mapToEntity(commentDto);
    //Retrieve post from database using postId
    Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

    //set post to comment entity
    comment.setPost(post);


    //save comment to database
    Comment newComment = commentRepository.save(comment);
    return mapToDto(newComment);
  }


  //GET ALL COMMENTS BY POST ID

  public CommentDto getCommentById(Long postId, Long commentId) {
    //Retrieve post from database using postId
    Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

    //Retrieve comment from database using commentId
    Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));

    //check if comment belongs to post
    if (comment.getPost().getId() != post.getId()) {
      throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
    }
    return mapToDto(comment);

  }

  //UPDATE COMMENT
  @Override
  public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
    Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

    Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));

    if (comment.getPost().getId() != post.getId()) {
      throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
    }
    comment.setName(commentRequest.getName());
    comment.setEmail(commentRequest.getEmail());
    comment.setBody(commentRequest.getBody());

    Comment updatedComment = commentRepository.save(comment);
    return mapToDto(updatedComment);
  }


  //DELETE COMMENT
  @Override
  public void deleteComment(Long postId, Long commentId) {
    Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
    Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));

    if (comment.getPost().getId() != post.getId()) {
      throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
    }
    commentRepository.delete(comment);
  }


  @Override
  public List<CommentDto> getAllCommentsByPostId(Long postId) {
    List<Comment> commentDtoList = commentRepository.findByPostId(postId);
    return commentDtoList.stream().map(this::mapToDto).collect(Collectors.toList());
  }


  //Converter methods
  private CommentDto mapToDto(Comment comment) {
    return mapper.map(comment, CommentDto.class);
  }

  private Comment mapToEntity(CommentDto commentDto) {
    return mapper.map(commentDto, Comment.class);
  }
}
