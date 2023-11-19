package com.lolz.blog.controller;


import com.lolz.blog.payload.CommentDto;
import com.lolz.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {


  private final CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }


  @PostMapping("/posts/{postId}/comments")
  public ResponseEntity<CommentDto> createComment(@Valid @PathVariable Long postId, @RequestBody CommentDto commentDto) {
    return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
  }

  @GetMapping("/posts/{postId}/comments")
  public List<CommentDto> getAllCommentsByPostId(@PathVariable Long postId) {
    return commentService.getAllCommentsByPostId(postId);
  }

  @GetMapping("/posts/{postId}/comments/{commentId}")
  public ResponseEntity<CommentDto> getCommentById(@PathVariable Long postId, @PathVariable Long commentId) {
    return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
  }

  @PutMapping("/posts/{postId}/comments/{commentId}")
  public ResponseEntity<CommentDto> updateComment(@Valid @PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentDto commentDto) {
    return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.OK);
  }

  @DeleteMapping("/posts/{postId}/comments/{commentId}")
  public ResponseEntity<?> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
    commentService.deleteComment(postId, commentId);
    return new ResponseEntity<>(HttpStatus.OK);
  }




}
