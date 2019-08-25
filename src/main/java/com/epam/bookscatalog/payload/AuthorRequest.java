package com.epam.bookscatalog.payload;

import com.epam.bookscatalog.model.Author;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public class AuthorRequest {

  /*private Long id;

  @NaturalId
  @NotBlank
  @Size(max = 13)
  private String isbn;

  @NotBlank
  @Size(max = 100)
  private String name;

  @Temporal(TemporalType.DATE)
  private Date publishedDate;

  *//*@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
  private Set<Comment> comments;*//*

  @Range(min = 0, max = 5)
  @Column(columnDefinition = "integer default 0")
  private int rating;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "book_authors",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "author_id"))
  private Set<Author> authors = new HashSet<>();

  @ElementCollection
  private Set<String> genres = new HashSet<>();

  @ElementCollection
  private Set<String> languages = new HashSet<>();;*/

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