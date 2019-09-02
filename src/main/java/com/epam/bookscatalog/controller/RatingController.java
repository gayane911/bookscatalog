package com.epam.bookscatalog.controller;

import com.epam.bookscatalog.dto.ApiMessageDto;
import com.epam.bookscatalog.payload.RatingRequest;
import com.epam.bookscatalog.repository.RatingRepository;
import com.epam.bookscatalog.security.CurrentUser;
import com.epam.bookscatalog.security.UserPrincipal;
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
public class RatingController {

  @Autowired
  private RatingRepository ratingRepository;

  //@Autowired
  //private PollRepository pollRepository;

  //@Autowired
  //private VoteRepository voteRepository;

  @Autowired
  private RatingService ratingService;

  private static final Logger logger = LoggerFactory.getLogger(RatingController.class);


  @PostMapping("/rating")
  //@PreAuthorize("hasRole('USER')")
  @RolesAllowed("ROLE_READER")
  public ResponseEntity<ApiMessageDto> rate(@Valid @RequestBody RatingRequest ratingRequest,
      @CurrentUser UserPrincipal currentUser) {

    ratingService.rate(ratingRequest, currentUser.getId());
    //Author author = authorService.createAuthor(authorRequest);

    /*URI location = ServletUriComponentsBuilder
        .fromCurrentRequest().path("/{author}")
        .buildAndExpand(author.getId()).toUri();*/

    return ResponseEntity/*.created(location)*/
        .ok(new ApiMessageDto(true,
            String.format("Book with id %s rated successfully", ratingRequest.getBookId())));
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