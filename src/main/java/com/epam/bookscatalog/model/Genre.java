package com.epam.bookscatalog.model;

import com.epam.bookscatalog.model.audit.DateAudit;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "genres", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "genre"
    })
})
public class Genre extends DateAudit {

  /*(id, ISBN, name, published date, comments, rating , genres, languages
and etc.), Author (id, name and etc.).*/

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NaturalId
  @NotBlank
  private String genre;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }
}