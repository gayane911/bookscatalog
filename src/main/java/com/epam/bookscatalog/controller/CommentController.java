package com.epam.bookscatalog.controller;

import com.epam.bookscatalog.dto.ApiMessageDto;
import com.epam.bookscatalog.payload.CommentRequest;
import com.epam.bookscatalog.security.CurrentUser;
import com.epam.bookscatalog.security.UserPrincipal;
import com.epam.bookscatalog.service.CommentService;
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
  private CommentService commentService;

  private static final Logger logger = LoggerFactory.getLogger(CommentController.class);


  @PostMapping("/comment")
  @RolesAllowed("ROLE_READER")
  public ResponseEntity<ApiMessageDto> rate(@Valid @RequestBody CommentRequest commentRequest,
      @CurrentUser UserPrincipal currentUser) {

    commentService.createComment(commentRequest, currentUser.getId());
    return ResponseEntity
        .ok(new ApiMessageDto(true,
            String.format("Comment is successfully added to Book with id %s.", commentRequest
                .getBookId())));
  }
}