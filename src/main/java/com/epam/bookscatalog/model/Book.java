package com.epam.bookscatalog.model;

import com.epam.bookscatalog.model.audit.DateAudit;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "books", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "isbn"
    })
})
public class Book extends DateAudit {

  /*(id, ISBN, name, published date, comments, rating , genres, languages
and etc.), Author (id, name and etc.).*/

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NaturalId
  @NotBlank
  @Size(max = 13)
  private String isbn;

  @NotBlank
  @Size(max = 100)
  private String title;

  @Temporal(TemporalType.DATE)
  private Date publishedDate;

  /*@Range(min = 0, max = 5)
  @Column(columnDefinition = "integer default 0")
  private int rating;*/

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "bookId")
  private Set<Rating> ratings = new HashSet<>();

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "book_authors",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "author_id"))
  private Set<Author> authors = new HashSet<>();

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "bookId")
  private Set<Comment> comments = new HashSet<>();

  @ElementCollection
  private Set<String> genres = new HashSet<>();

  @ElementCollection
  private Set<String> languages = new HashSet<>();

  @Transient
  private double overallRating;

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

  public Date getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(Date publishedDate) {
    this.publishedDate = publishedDate;
  }

  /*public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }*/

  public Set<Rating> getRatings() {
    return ratings;
  }

  public void setRatings(Set<Rating> ratings) {
    this.ratings = ratings;
  }

  public Set<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(Set<Author> authors) {
    this.authors = authors;
  }

  public Set<Comment> getComments() {
    return comments;
  }

  public void setComments(Set<Comment> comments) {
    this.comments = comments;
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

  public void addAuthor(Author author) {
    this.authors.add(author);
  }

  public double getOverallRating() {
    return overallRating;
  }

  @PostLoad
  void fillTransient() {
    if (!ratings.isEmpty()) {
      int ratingSum = 0;
      for (Rating r : ratings) {
        ratingSum += r.getRating().getValue();
      }
      overallRating = ratingSum / ratings.size();
    }
  }
}