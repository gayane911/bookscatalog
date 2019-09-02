package com.epam.bookscatalog.controller;

import com.epam.bookscatalog.dto.UserProfileDto;
import com.epam.bookscatalog.payload.UserIdentityAvailability;
import com.epam.bookscatalog.service.UserService;
import javax.annotation.security.RolesAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  private UserService userService;

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @GetMapping("/user/checkUsernameAvailability")
  public ResponseEntity<UserIdentityAvailability> checkUsernameAvailability(
      @RequestParam(value = "username") String username) {
    Boolean isAvailable = !userService.existsByUsername(username);
    return ResponseEntity.ok(new UserIdentityAvailability(isAvailable));
  }

  @GetMapping("/user/checkEmailAvailability")
  public ResponseEntity<UserIdentityAvailability> checkEmailAvailability(
      @RequestParam(value = "email") String email) {
    Boolean isAvailable = !userService.existsByEmail(email);
    return ResponseEntity.ok(new UserIdentityAvailability(isAvailable));
  }

  @GetMapping("/users/{username}")
  @RolesAllowed("ROLE_ADMIN")
  public ResponseEntity<UserProfileDto> getUserProfile(
      @PathVariable(value = "username") String username) {
    UserProfileDto userProfileDto = userService.findByUsername(username);
    return ResponseEntity.ok(userProfileDto);
  }
}