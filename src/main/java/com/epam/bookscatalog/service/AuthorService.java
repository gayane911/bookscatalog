package com.epam.bookscatalog.service;

import com.epam.bookscatalog.model.Author;
import com.epam.bookscatalog.model.Book;
import com.epam.bookscatalog.payload.AuthorRequest;
import com.epam.bookscatalog.payload.BookRequest;
import com.epam.bookscatalog.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

  @Autowired
  private AuthorRepository authorRepository;

  public Author createAuthor(AuthorRequest authorRequest) {
    Author author = new Author();
    author.setFullName(authorRequest.getFullName());
    return authorRepository.save(author);
  }


}
