package com.lolz.blog.repository;

import com.lolz.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository  extends JpaRepository<Post,Long> {

}