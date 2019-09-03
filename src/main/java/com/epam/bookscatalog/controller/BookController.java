package com.epam.bookscatalog.controller;

import com.epam.bookscatalog.dto.ApiMessageDto;
import com.epam.bookscatalog.dto.BookDto;
import com.epam.bookscatalog.payload.BookRequest;
import com.epam.bookscatalog.security.CurrentUser;
import com.epam.bookscatalog.security.UserPrincipal;
import com.epam.bookscatalog.service.BookService;
import java.util.Set;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookController {

  @Autowired
  private BookService bookService;

  @PostMapping("/books")
  @RolesAllowed("ROLE_ADMIN")
  public ResponseEntity<ApiMessageDto> createBook(@Valid @RequestBody BookRequest bookRequest) {
    bookService.createBook(bookRequest);
    return ResponseEntity.ok(new ApiMessageDto(true, "Book Created Successfully"));
  }

  @GetMapping("/books")
  public ResponseEntity<Set<BookDto>> getAllBooks() {
    Set<BookDto> bookResponses = bookService.getAllBooks();
    return ResponseEntity.ok(bookResponses);
  }

  @PutMapping("/books/favorite/{bookId}")
  @RolesAllowed("ROLE_READER")
  public ResponseEntity<ApiMessageDto> addBookToFavorites(@CurrentUser UserPrincipal currentUser,
      @PathVariable(value = "bookId") Long bookId) {
    bookService.addToFavorite(bookId, currentUser.getId());
    return ResponseEntity.ok(new ApiMessageDto(true,
        String.format("Book with id %s successfully added to favorites.", bookId)));
  }

  @GetMapping("/books/search")
  @RolesAllowed("ROLE_READER")
  public ResponseEntity<Set<BookDto>> searchBookByTitle(
      @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "author", required = false) String author,
      @RequestParam(value = "language", required = false) String language,
      @RequestParam(value = "genre", required = false) String genre) {
    Set<BookDto> bookResponses = bookService.search(title, author, language, genre);
    return ResponseEntity.ok(bookResponses);
  }

  @GetMapping("/books/search/recent")
  @RolesAllowed("ROLE_READER")
  public ResponseEntity<Set<BookDto>> getRecentBooks() {
    Set<BookDto> bookResponses = bookService.getRecentBooks();
    return ResponseEntity.ok(bookResponses);
  }
}
