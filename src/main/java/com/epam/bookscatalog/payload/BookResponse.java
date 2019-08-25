package com.epam.bookscatalog.payload;

import com.epam.bookscatalog.model.Author;
import com.epam.bookscatalog.model.Rating;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.validator.constraints.Range;

public class BookResponse {
  private Long id;
  private String isbn;
  private String title;
  private Date publishedDate;
  private Set<CommentResponse> comments = new HashSet<>();
  private Set<RatingResponse> ratings = new HashSet<>();
  private Set<AuthorResponse> authors = new HashSet<>();
  private Set<String> languages;
  private Set<String> genres;
  private Double overallRating;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(Date publishedDate) {
    this.publishedDate = publishedDate;
  }

  /*public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }*/

  public Set<RatingResponse> getRatings() {
    return ratings;
  }

  public void setRatings(Set<RatingResponse> ratings) {
    this.ratings = ratings;
  }

  public Set<AuthorResponse> getAuthors() {
    return authors;
  }

  public void setAuthors(Set<AuthorResponse> authors) {
    this.authors = authors;
  }

  public Set<CommentResponse> getComments() {
    return comments;
  }

  public void setComments(Set<CommentResponse> comments) {
    this.comments = comments;
  }

  public Set<String> getLanguages() {
    return languages;
  }

  public void setLanguages(Set<String> languages) {
    this.languages = languages;
  }

  public Set<String> getGenres() {
    return genres;
  }

  public void setGenres(Set<String> genres) {
    this.genres = genres;
  }

  public void addAuthorResponse(AuthorResponse authorResponse) {
    this.authors.add(authorResponse);
  }

  public void addRatingResponse(RatingResponse ratingResponse) {
    this.ratings.add(ratingResponse);
  }

  public void addCommentResponse(CommentResponse commentResponse) {
    this.comments.add(commentResponse);
  }

  public Double getOverallRating() {
    return overallRating;
  }

  public void setOverallRating(Double overallRating) {
    this.overallRating = overallRating;
  }
}