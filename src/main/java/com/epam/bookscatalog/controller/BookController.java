package com.epam.bookscatalog.controller;

import com.epam.bookscatalog.model.Book;
import com.epam.bookscatalog.model.User;
import com.epam.bookscatalog.payload.ApiResponse;
import com.epam.bookscatalog.payload.BookRequest;
import com.epam.bookscatalog.payload.BookResponse;
import com.epam.bookscatalog.repository.BookRepository;
import com.epam.bookscatalog.security.CurrentUser;
import com.epam.bookscatalog.security.UserPrincipal;
import com.epam.bookscatalog.service.BookService;
import java.net.URI;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
  public ResponseEntity<ApiResponse> createBook(@Valid @RequestBody BookRequest bookRequest) {
    Book book = bookService.createBook(bookRequest);

    /*URI location = ServletUriComponentsBuilder
        .fromCurrentRequest().path("/{bookId}")
        .buildAndExpand(book.getId()).toUri();*/

    return ResponseEntity/*created(location)*/
        .ok(new ApiResponse(true, "Book Created Successfully"));
  }

  @GetMapping("/books")
  public ResponseEntity<Set<BookResponse>> getAllBooks() {
    Set<BookResponse> bookResponses = bookService.getAllBooks();
    return ResponseEntity.ok(bookResponses);
  }

  @PutMapping("/books/favorite/{bookId}")
  @RolesAllowed("ROLE_READER")
  public ResponseEntity<ApiResponse> addBookToFavorites(@CurrentUser UserPrincipal currentUser, @PathVariable(value = "bookId") Long bookId) {
    bookService.addToFavorite(bookId, currentUser.getId());
    return ResponseEntity.ok(new ApiResponse(true, String.format("Book with id %s successfully added to favorites.", bookId)));
  }

  /*@GetMapping("/user/me")
  //@PreAuthorize("hasRole('READER')")
  @RolesAllowed({"ROLE_ADMIN", "ROLE_READER"})
  public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
    UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(),
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
  public UserProfile getUserProfile(@CurrentUser UserPrincipal currentUser, @PathVariable(value = "username") String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

    //long pollCount = pollRepository.countByCreatedBy(user.getId());
    //long voteCount = voteRepository.countByUserId(user.getId());

    UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(),
        user.getCreatedAt()*//*, pollCount, voteCount*//*);

    return userProfile;
  }*/

 /* @GetMapping("/users/{username}/polls")
  public PagedResponse<BookResponse> getPollsCreatedBy(
      @PathVariable(value = "username") String username,
      @CurrentUser UserPrincipal currentUser,
      @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
      @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
    return pollService.getPollsCreatedBy(username, currentUser, page, size);
  }*/


  /*@GetMapping("/users/{username}/votes")
  public PagedResponse<BookResponse> getPollsVotedBy(
      @PathVariable(value = "username") String username,
      @CurrentUser UserPrincipal currentUser,
      @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
      @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
    return pollService.getPollsVotedBy(username, currentUser, page, size);
  }
*/
}