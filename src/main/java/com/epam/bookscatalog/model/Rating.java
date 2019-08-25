package com.epam.bookscatalog.model;

import com.epam.bookscatalog.payload.RatingResponse;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
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

  public RatingResponse getRatingResponse() {
    RatingResponse ratingResponse = new RatingResponse();
    ratingResponse.setBookId(ratingIdentity.getBookId());
    ratingResponse.setUserId(ratingIdentity.getUserId());
    ratingResponse.setRating(getRating());
    return ratingResponse;
  }
}
