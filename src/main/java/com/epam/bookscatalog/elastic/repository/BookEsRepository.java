package com.epam.bookscatalog.elastic.repository;

import com.epam.bookscatalog.elastic.model.BookEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookEsRepository extends ElasticsearchRepository<BookEs, Long> {

}
