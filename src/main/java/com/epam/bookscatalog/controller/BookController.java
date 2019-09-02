package com.epam.bookscatalog.controller;

import com.epam.bookscatalog.dto.BookDto;
import com.epam.bookscatalog.model.Book;
import com.epam.bookscatalog.dto.ApiMessageDto;
import com.epam.bookscatalog.payload.BookRequest;
import com.epam.bookscatalog.repository.BookRepository;
import com.epam.bookscatalog.security.CurrentUser;
import com.epam.bookscatalog.security.UserPrincipal;
import com.epam.bookscatalog.service.BookService;
import java.util.Set;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  private BookRepository bookRepository;

  //@Autowired
  //private PollRepository pollRepository;

  //@Autowired
  //private VoteRepository voteRepository;

  @Autowired
  private BookService bookService;

  private static final Logger logger = LoggerFactory.getLogger(BookController.class);


  @PostMapping("/books")
  //@PreAuthorize("hasRole('USER')")
  @RolesAllowed("ROLE_ADMIN")
  public ResponseEntity<ApiMessageDto> createBook(@Valid @RequestBody BookRequest bookRequest) {
    Book book = bookService.createBook(bookRequest);

    /*URI location = ServletUriComponentsBuilder
        .fromCurrentRequest().path("/{bookId}")
        .buildAndExpand(book.getId()).toUri();*/

    return ResponseEntity/*created(location)*/
        .ok(new ApiMessageDto(true, "Book Created Successfully"));
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

  /*@GetMapping("/user/me")
  //@PreAuthorize("hasRole('READER')")
  @RolesAllowed({"ROLE_ADMIN", "ROLE_READER"})
  public UserSummaryDto getCurrentUser(@CurrentUser UserPrincipal currentUser) {
    UserSummaryDto userSummary = new UserSummaryDto(currentUser.getId(), currentUser.getUsername(),
        currentUser.getName(), currentUser.getRoles());
    return userSummary;
  }

  @GetMapping("/user/checkUsernameAvailability")
  public UserIdentityAvailability checkUsernameAvailability(
      @RequestParam(value = "username") String username) {
    Boolean isAvailable = !userRepository.existsByUsername(username);
    return new UserIdentityAvailability(isAvailable);
  }

  @GetMapping("/user/checkEmailAvailability")
  public UserIdentityAvailability checkEmailAvailability(
      @RequestParam(value = "email") String email) {
    Boolean isAvailable = !userRepository.existsByEmail(email);
    return new UserIdentityAvailability(isAvailable);
  }

  @GetMapping("/users/{username}")
  //@PreAuthorize("hasRole('ROLE_READER')")
  @RolesAllowed("ROLE_ADMIN")
  public UserProfileDto getUserProfile(@CurrentUser UserPrincipal currentUser, @PathVariable(value = "username") String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

    //long pollCount = pollRepository.countByCreatedBy(user.getId());
    //long voteCount = voteRepository.countByUserId(user.getId());

    UserProfileDto userProfile = new UserProfileDto(user.getId(), user.getUsername(), user.getName(),
        user.getCreatedAt()*//*, pollCount, voteCount*//*);

    return userProfile;
  }*/

 /* @GetMapping("/users/{username}/polls")
  public PagedResponse<BookDto> getPollsCreatedBy(
      @PathVariable(value = "username") String username,
      @CurrentUser UserPrincipal currentUser,
      @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
      @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
    return pollService.getPollsCreatedBy(username, currentUser, page, size);
  }*/


  /*@GetMapping("/users/{username}/votes")
  public PagedResponse<BookDto> getPollsVotedBy(
      @PathVariable(value = "username") String username,
      @CurrentUser UserPrincipal currentUser,
      @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
      @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
    return pollService.getPollsVotedBy(username, currentUser, page, size);
  }
*/
}
