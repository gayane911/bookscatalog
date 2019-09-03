package com.epam.bookscatalog.repository;

import com.epam.bookscatalog.model.Author;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

  Optional<Author> findByFullName(String fullName);

}