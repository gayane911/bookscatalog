package com.epam.bookscatalog.payload;

import javax.validation.constraints.NotNull;

public class CommentRequest {

  @NotNull
  private Long bookId;

  @NotNull
  private String text;

  public Long getBookId() {
    return bookId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}