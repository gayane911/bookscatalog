package com.epam.bookscatalog.model;

import com.epam.bookscatalog.dto.RatingDto;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ratings")
public class Rating {

  /*@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;*/

  /*@NotNull
  private Long userId;

  @NotNull
  private Long bookId;*/

  @EmbeddedId
  private RatingIdentity ratingIdentity;

  @NotNull
  private Integer ratingValue;

  @Transient
  private RatingName rating;

  /*public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }*/

  /*public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getBookId() {
    return bookId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }*/

  public RatingIdentity getRatingIdentity() {
    return ratingIdentity;
  }

  public void setRatingIdentity(RatingIdentity ratingIdentity) {
    this.ratingIdentity = ratingIdentity;
  }

  public RatingName getRating() {
    return rating;
  }

  public void setRating(RatingName rating) {
    this.rating = rating;
    if (rating != null) {
      this.ratingValue = rating.getValue();
    }
  }

  @PostLoad
  void fillTransient() {
    this.rating = RatingName.getByValue(ratingValue);
  }

  /*@PrePersist
  void fillPersistent() {
    if (rating != null) {
      this.ratingValue = rating.getValue();
    }
  }*/

  public RatingDto getRatingResponse() {
    RatingDto ratingDto = new RatingDto();
    ratingDto.setBookId(ratingIdentity.getBookId());
    ratingDto.setUserId(ratingIdentity.getUserId());
    ratingDto.setRating(getRating());
    return ratingDto;
  }
}
