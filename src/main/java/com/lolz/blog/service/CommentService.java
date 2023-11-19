package com.lolz.blog.service;

import com.lolz.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {


  //create comment
  CommentDto createComment(Long postId,CommentDto commentDto);


  //get all comments by post id
  List<CommentDto> getAllCommentsByPostId(Long postId);



//get comment by id
  CommentDto getCommentById(Long postId, Long commentId);

  //update comment
  CommentDto updateComment(Long postId,Long commentId,CommentDto commentRequest);

  //delete comment
  void deleteComment(Long postId,Long commentId);






}
