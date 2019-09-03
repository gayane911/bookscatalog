package com.epam.bookscatalog.controller;

import com.epam.bookscatalog.dto.ApiMessageDto;
import com.epam.bookscatalog.payload.AuthorRequest;
import com.epam.bookscatalog.service.AuthorService;
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
public class AuthorController {

  @Autowired
  private AuthorService authorService;

  @PostMapping("/authors")
  @RolesAllowed("ROLE_ADMIN")
  public ResponseEntity<ApiMessageDto> createAuthor(
      @Valid @RequestBody AuthorRequest authorRequest) {
    authorService.createAuthor(authorRequest);
    return ResponseEntity.ok(new ApiMessageDto(true, "Author Created Successfully"));
  }
}