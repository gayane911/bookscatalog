package com.epam.bookscatalog.repository;

import com.epam.bookscatalog.model.Author;
import com.epam.bookscatalog.model.Book;
import com.epam.bookscatalog.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  Optional<Book> findByTitle(String title);

  Optional<Book> findByIsbn(String isbn);

  Optional<Book> findByAuthors_fullName(String author);

  Boolean existsByIsbn(String isbn);

}