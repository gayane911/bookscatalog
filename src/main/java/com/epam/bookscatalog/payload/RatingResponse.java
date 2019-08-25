package com.epam.bookscatalog.payload;

import com.epam.bookscatalog.model.RatingName;

public class RatingResponse {

  private Long bookId;
  private Long userId;
  private RatingName rating;

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

  public RatingName getRating() {
    return rating;
  }

  public void setRating(RatingName rating) {
    this.rating = rating;
  }
}
