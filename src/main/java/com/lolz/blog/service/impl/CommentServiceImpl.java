package com.lolz.blog.service.impl;

import com.lolz.blog.entity.CommentDto;
import com.lolz.blog.repository.CommentRepository;
import com.lolz.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl  implements CommentService  {


  private CommentServiceImpl commentServiceImpl;

  public CommentServiceImpl(CommentServiceImpl commentServiceImpl) {
    this.commentServiceImpl = commentServiceImpl;
  }

  public CommentDto createComment(CommentDto commentDto) {
    return null;
  }
}
