package com.epam.bookscatalog.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorRequest {

  @NotBlank
  @Size(max = 100)
  private String fullName;

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
}