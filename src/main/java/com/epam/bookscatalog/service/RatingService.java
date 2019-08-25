package com.epam.bookscatalog.service;

import com.epam.bookscatalog.model.Rating;
import com.epam.bookscatalog.model.RatingIdentity;
import com.epam.bookscatalog.model.RatingName;
import com.epam.bookscatalog.payload.RatingRequest;
import com.epam.bookscatalog.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

  @Autowired
  private RatingRepository ratingRepository;

  public Rating rate(RatingRequest ratingRequest, Long userId) {
    /*Author author = new Author();
    author.setFullName(authorRequest.getFullName());
    return authorRepository.save(author);*/

    Rating rating = new Rating();
    rating.setRating(RatingName.getByValue(ratingRequest.getRating()));
    //rating.setRatingValue(ratingRequest.getRating());
    //rating.setRating(RatingName.RATING_5);
    RatingIdentity ratingIdentity = new RatingIdentity();
    ratingIdentity.setBookId(ratingRequest.getBookId());
    ratingIdentity.setUserId(userId);
    rating.setRatingIdentity(ratingIdentity);

    ratingRepository.save(rating);

    return rating;
  }


}
