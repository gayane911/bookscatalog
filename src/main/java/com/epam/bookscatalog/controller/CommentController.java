package com.epam.bookscatalog.controller;

import com.epam.bookscatalog.payload.ApiResponse;
import com.epam.bookscatalog.payload.CommentRequest;
import com.epam.bookscatalog.payload.RatingRequest;
import com.epam.bookscatalog.repository.CommentRepository;
import com.epam.bookscatalog.repository.RatingRepository;
import com.epam.bookscatalog.security.CurrentUser;
import com.epam.bookscatalog.security.UserPrincipal;
import com.epam.bookscatalog.service.CommentService;
import com.epam.bookscatalog.service.RatingService;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CommentController {

  @Autowired
  private CommentRepository commentRepository;

  //@Autowired
  //private PollRepository pollRepository;

  //@Autowired
  //private VoteRepository voteRepository;

  @Autowired
  private CommentService commentService;

  private static final Logger logger = LoggerFactory.getLogger(CommentController.class);


  @PostMapping("/comment")
  //@PreAuthorize("hasRole('USER')")
  @RolesAllowed("ROLE_READER")
  public ResponseEntity<ApiResponse> rate(@Valid @RequestBody CommentRequest commentRequest,
      @CurrentUser UserPrincipal currentUser) {

    commentService.createComment(commentRequest, currentUser.getId());
    //Author author = authorService.createAuthor(authorRequest);

    /*URI location = ServletUriComponentsBuilder
        .fromCurrentRequest().path("/{author}")
        .buildAndExpand(author.getId()).toUri();*/

    return ResponseEntity/*.created(location)*/
        .ok(new ApiResponse(true,
            String.format("Comment is successfully added to Book with id %s.", commentRequest.getBookId())));
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