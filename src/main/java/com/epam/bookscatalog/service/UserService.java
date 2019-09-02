package com.epam.bookscatalog.service;

import com.epam.bookscatalog.dto.UserProfileDto;
import com.epam.bookscatalog.exception.ResourceNotFoundException;
import com.epam.bookscatalog.model.User;
import com.epam.bookscatalog.repository.UserRepository;
import com.epam.bookscatalog.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  public UserProfileDto findByUsername(String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    return ModelConverter.getUserProfileDto(user);
  }

}
