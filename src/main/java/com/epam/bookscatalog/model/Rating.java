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

  @EmbeddedId
  private RatingIdentity ratingIdentity;

  @NotNull
  private Integer ratingValue;

  @Transient
  private RatingName rating;

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

  public RatingDto getRatingResponse() {
    RatingDto ratingDto = new RatingDto();
    ratingDto.setBookId(ratingIdentity.getBookId());
    ratingDto.setUserId(ratingIdentity.getUserId());
    ratingDto.setRating(getRating());
    return ratingDto;
  }
}
