package com.epam.bookscatalog.model;

import com.epam.bookscatalog.model.audit.DateAudit;
import com.epam.bookscatalog.payload.AuthorResponse;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "authors", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "fullName"
    })
})
public class Author extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NaturalId
  @NotBlank
  @Size(max = 100)
  private String fullName;

  public Author() {

  }

  public Author(String fullName) {
    this.fullName = fullName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public AuthorResponse getAuthorResponse() {
    AuthorResponse authorResponse = new AuthorResponse();
    authorResponse.setId(getId());
    authorResponse.setFullName(getFullName());
    return authorResponse;
  }
}