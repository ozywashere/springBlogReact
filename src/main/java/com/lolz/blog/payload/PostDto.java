package com.lolz.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto {

  private long id;

  //title should not be null or empty
  //title should have at least 2 characters

  @NotEmpty(message = "Title should not be empty")
  @Size(min = 2, message = "Title should have at least 2 characters")
  private String title;

  @NotEmpty(message = "Description should not be empty")
  @Size(min = 10, message = "Description should have at least 10 characters")
  private String description;

  @NotEmpty(message = "Content should not be empty")
  @Size(min = 10, message = "Content should have at least 100 characters")
  private String content;


  private Set<CommentDto> comments;
}
