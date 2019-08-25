package com.epam.bookscatalog.payload;

import javax.validation.constraints.NotNull;

public class RatingRequest {

  @NotNull
  private Long bookId;

  @NotNull
  private Integer rating;

  public Long getBookId() {
    return bookId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }
}