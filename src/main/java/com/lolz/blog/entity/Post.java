package com.lolz.blog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts",uniqueConstraints={@UniqueConstraint(columnNames={"title"})})
@Data
@AllArgsConstructor
 @NoArgsConstructor
@Getter
@Setter
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title",nullable = false)
  private String title;

  @Column(name = "description",nullable = false)
  private String description;

  @Column(name = "content",nullable = false)
  private String content;

  @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)

  private Set<Comment> comments=new HashSet<>();


}
