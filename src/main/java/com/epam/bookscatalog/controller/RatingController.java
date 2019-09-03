package com.epam.bookscatalog.controller;

import com.epam.bookscatalog.dto.ApiMessageDto;
import com.epam.bookscatalog.payload.RatingRequest;
import com.epam.bookscatalog.security.CurrentUser;
import com.epam.bookscatalog.security.UserPrincipal;
import com.epam.bookscatalog.service.RatingService;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
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
  private RatingService ratingService;

  @PostMapping("/rating")
  @RolesAllowed("ROLE_READER")
  public ResponseEntity<ApiMessageDto> rate(@Valid @RequestBody RatingRequest ratingRequest,
      @CurrentUser UserPrincipal currentUser) {

    ratingService.rate(ratingRequest, currentUser.getId());
    return ResponseEntity
        .ok(new ApiMessageDto(true,
            String.format("Book with id %s rated successfully", ratingRequest.getBookId())));
  }
}