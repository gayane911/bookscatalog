package com.epam.bookscatalog.dto;

import java.util.HashSet;
import java.util.Set;

public class UserProfileDto {

  private Long id;
  private String username;
  private String name;
  private String email;
  private Set<BookDto> favorites = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<BookDto> getFavorites() {
    return favorites;
  }

  public void setFavorites(Set<BookDto> favorites) {
    this.favorites = favorites;
  }

  public void addFavorite(BookDto bookDto) {
    this.favorites.add(bookDto);
  }
}