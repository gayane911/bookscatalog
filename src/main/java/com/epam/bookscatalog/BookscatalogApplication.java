package com.epam.bookscatalog;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EnableElasticsearchRepositories("com.epam.bookscatalog.elastic.repository")
@SpringBootApplication
@EntityScan(basePackageClasses = {
    BookscatalogApplication.class,
    Jsr310JpaConverters.class
})
public class BookscatalogApplication {

  @PostConstruct
  void init() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

  public static void main(String[] args) {
    SpringApplication.run(BookscatalogApplication.class, args);
  }

}

