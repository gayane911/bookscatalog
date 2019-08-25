package com.epam.bookscatalog.repository;

import com.epam.bookscatalog.model.Author;
import com.epam.bookscatalog.model.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

  Optional<Author> findByFullName(String fullName);

  /*Optional<Book> findByTitle(String title);

  Optional<Book> findByIsbn(String isbn);

  Optional<Book> findByAuthors_Author(Author author);

  Boolean existsByIsbn(String isbn);*/

}