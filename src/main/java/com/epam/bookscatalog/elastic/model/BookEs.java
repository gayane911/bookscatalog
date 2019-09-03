package com.epam.bookscatalog.elastic.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Book model for elastic search
 */
@Document(indexName = "booksindex", type = "books")
public class BookEs implements Serializable {

  @Id
  private Long id;

  @Field(type = FieldType.Text, store = true)
  private String isbn;

  @Field(type = FieldType.Text, store = true)
  private String title;

  @Field(type = FieldType.Text, store = true)
  private Set<String> authors = new HashSet<>();

  @Field(type = FieldType.Text, store = true)
  private Set<String> genres = new HashSet<>();

  @Field(type = FieldType.Text, store = true)
  private Set<String> languages = new HashSet<>();

  @Field(type = FieldType.Long, store = true)
  private Long creationDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public void addAuthor(String author) {
    this.authors.add(author);
  }

  public Long getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Long creationDate) {
    this.creationDate = creationDate;
  }
}
