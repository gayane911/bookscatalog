package com.epam.bookscatalog.util;

import com.epam.bookscatalog.dto.BookDto;
import com.epam.bookscatalog.dto.UserProfileDto;
import com.epam.bookscatalog.elastic.model.BookEs;
import com.epam.bookscatalog.model.Author;
import com.epam.bookscatalog.model.Book;
import com.epam.bookscatalog.model.Comment;
import com.epam.bookscatalog.model.Rating;
import com.epam.bookscatalog.model.User;

public class ModelConverter {

  /**
   * Converts Book model to BookDto
   *
   * @param book that should be converted
   * @return instance of BookDto
   */
  public static BookDto getBookDto(Book book) {
    BookDto bookDto = new BookDto();
    bookDto.setId(book.getId());
    bookDto.setTitle(book.getTitle());
    bookDto.setIsbn(book.getIsbn());

    for (Comment comment : book.getComments()) {
      bookDto.addCommentResponse(comment.getCommentRespopnse());
    }

    for (Rating rating : book.getRatings()) {
      bookDto.addRatingResponse(rating.getRatingResponse());
    }
    bookDto.setOverallRating(book.getOverallRating());
    bookDto.setPublishedDate(book.getPublishedDate());

    for (Author ar : book.getAuthors()) {
      bookDto.addAuthor(ar.getFullName());
    }

    bookDto.setGenres(book.getGenres());
    bookDto.setLanguages(book.getLanguages());

    return bookDto;
  }

  /**
   * Converts BookEs elastic search model to BookDto
   *
   * @param bookEs that should be converted
   * @return instance of BookDto
   */
  public static BookDto getBookDto(BookEs bookEs) {
    BookDto bookDto = new BookDto();
    bookDto.setId(bookEs.getId());
    bookDto.setTitle(bookEs.getTitle());
    bookDto.setIsbn(bookEs.getIsbn());
    bookDto.setAuthors(bookEs.getAuthors());
    bookDto.setGenres(bookEs.getGenres());
    bookDto.setLanguages(bookEs.getLanguages());

    return bookDto;
  }

  /**
   * Converts Book model to BookEs elastic search model
   *
   * @param book that should be converted
   * @return instance of BookEs
   */
  public static BookEs getBookEs(Book book) {
    BookEs bookEs = new BookEs();
    bookEs.setId(book.getId());
    bookEs.setIsbn(book.getIsbn());
    bookEs.setTitle(book.getTitle());
    for (Author a : book.getAuthors()) {
      bookEs.addAuthor(a.getFullName());
    }
    bookEs.setGenres(book.getGenres());
    bookEs.setLanguages(book.getLanguages());
    return bookEs;
  }

  /**
   * Converts User model to UserProfileDto
   *
   * @param user that should be converted
   * @return instance of UserProfileDto
   */
  public static UserProfileDto getUserProfileDto(User user) {
    UserProfileDto userProfileDto = new UserProfileDto();
    userProfileDto.setId(user.getId());
    userProfileDto.setName(user.getName());
    userProfileDto.setUsername(user.getUsername());
    userProfileDto.setEmail(user.getEmail());

    for (Book book : user.getFavorites()) {
      userProfileDto.addFavorite(ModelConverter.getBookDto(book));
    }

    return userProfileDto;
  }
}
