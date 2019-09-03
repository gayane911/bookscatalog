package com.epam.bookscatalog.model;

import com.epam.bookscatalog.dto.CommentDto;
import com.epam.bookscatalog.model.audit.DateAudit;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comments")
public class Comment extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private Long bookId;

  @NotNull
  private Long userId;

  @NotBlank
  private String text;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getBookId() {
    return bookId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public CommentDto getCommentRespopnse() {
    CommentDto commentDto = new CommentDto();
    commentDto.setBookId(getBookId());
    commentDto.setUserId(getUserId());
    commentDto.setText(getText());
    return commentDto;
  }
}