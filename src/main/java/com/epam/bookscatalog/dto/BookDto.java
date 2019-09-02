package com.epam.bookscatalog.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class BookDto {
  private Long id;
  private String isbn;
  private String title;
  private Date publishedDate;
  private Set<CommentDto> comments = new HashSet<>();
  private Set<RatingDto> ratings = new HashSet<>();
  private Set<String> authors = new HashSet<>();
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

  public Set<RatingDto> getRatings() {
    return ratings;
  }

  public void setRatings(Set<RatingDto> ratings) {
    this.ratings = ratings;
  }

  public Set<String> getAuthors() {
    return authors;
  }

  public void setAuthors(Set<String> authors) {
    this.authors = authors;
  }

  public Set<CommentDto> getComments() {
    return comments;
  }

  public void setComments(Set<CommentDto> comments) {
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

  public void addAuthor(String author) {
    this.authors.add(author);
  }

  public void addRatingResponse(RatingDto ratingDto) {
    this.ratings.add(ratingDto);
  }

  public void addCommentResponse(CommentDto commentDto) {
    this.comments.add(commentDto);
  }

  public Double getOverallRating() {
    return overallRating;
  }

  public void setOverallRating(Double overallRating) {
    this.overallRating = overallRating;
  }
}