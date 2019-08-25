package com.epam.bookscatalog.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public class BookRequest {

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
  private Set<Author> authorRequests = new HashSet<>();

  @ElementCollection
  private Set<String> genres = new HashSet<>();

  @ElementCollection
  private Set<String> languages = new HashSet<>();;*/

  @NotBlank
  @Size(max = 13)
  private String isbn;

  @NotBlank
  @Size(max = 100)
  private String title;

  private Date publishedDate;

  private Set<String> authors = new HashSet<>();

  private Set<String> genres = new HashSet<>();

  private Set<String> languages = new HashSet<>();

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(Date publishedDate) {
    this.publishedDate = publishedDate;
  }

  public Set<String> getAuthors() {
    return authors;
  }

  public void setAuthors(Set<String> authors) {
    this.authors = authors;
  }

  public Set<String> getGenres() {
    return genres;
  }

  public void setGenres(Set<String> genres) {
    this.genres = genres;
  }

  public Set<String> getLanguages() {
    return languages;
  }

  public void setLanguages(Set<String> languages) {
    this.languages = languages;
  }
}