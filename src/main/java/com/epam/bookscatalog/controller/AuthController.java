package com.epam.bookscatalog.controller;

import com.epam.bookscatalog.dto.ApiMessageDto;
import com.epam.bookscatalog.dto.JwtAuthenticationDto;
import com.epam.bookscatalog.exception.AppException;
import com.epam.bookscatalog.model.Role;
import com.epam.bookscatalog.model.RoleName;
import com.epam.bookscatalog.model.User;
import com.epam.bookscatalog.payload.LoginRequest;
import com.epam.bookscatalog.payload.SignUpRequest;
import com.epam.bookscatalog.repository.RoleRepository;
import com.epam.bookscatalog.repository.UserRepository;
import com.epam.bookscatalog.security.JwtTokenProvider;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  JwtTokenProvider tokenProvider;

  @PostMapping("/signin")
  public ResponseEntity<JwtAuthenticationDto> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getUsernameOrEmail(),
            loginRequest.getPassword()
        )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.generateToken(authentication);
    return ResponseEntity.ok(new JwtAuthenticationDto(jwt));
  }

  @PostMapping("/signup")
  public ResponseEntity<ApiMessageDto> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return new ResponseEntity(new ApiMessageDto(false, "Username is already taken!"),
          HttpStatus.BAD_REQUEST);
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return new ResponseEntity(new ApiMessageDto(false, "Email Address already in use!"),
          HttpStatus.BAD_REQUEST);
    }

    // Creating user's account
    User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
        signUpRequest.getEmail(), signUpRequest.getPassword());

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    Set<Role> userRoles = new HashSet<>();

    if (CollectionUtils.isEmpty(signUpRequest.getRoles())) {
      Role readerRole = roleRepository.findByName(RoleName.ROLE_READER)
          .orElseThrow(() -> new AppException("User Role not set."));
      userRoles.add(readerRole);
    } else {
      for (RoleName rn : signUpRequest.getRoles()) {
        Role userRole = roleRepository.findByName(rn)
            .orElseThrow(() -> new AppException("User Role not set."));
        userRoles.add(userRole);
      }
    }

    user.setRoles(userRoles);
    userRepository.save(user);

    return ResponseEntity
        .ok(new ApiMessageDto(true, "User registered successfully"));
  }
}