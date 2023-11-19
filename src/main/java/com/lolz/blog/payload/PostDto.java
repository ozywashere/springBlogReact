package com.lolz.blog.payload;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto {
  private long id;
  private String title;
  private String description;
  private String content;
}
