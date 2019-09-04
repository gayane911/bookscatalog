package com.epam.bookscatalog.service;

import com.epam.bookscatalog.dto.BookDto;
import com.epam.bookscatalog.elastic.model.BookEs;
import com.epam.bookscatalog.elastic.repository.BookEsRepository;
import com.epam.bookscatalog.exception.ResourceNotFoundException;
import com.epam.bookscatalog.model.Author;
import com.epam.bookscatalog.model.Book;
import com.epam.bookscatalog.model.User;
import com.epam.bookscatalog.payload.BookRequest;
import com.epam.bookscatalog.repository.AuthorRepository;
import com.epam.bookscatalog.repository.BookRepository;
import com.epam.bookscatalog.repository.UserRepository;
import com.epam.bookscatalog.util.Constants;
import com.epam.bookscatalog.util.ModelConverter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private BookEsRepository bookEsRepository;

  @Autowired
  private AuthorRepository authorRepository;

  @Autowired
  private UserRepository userRepository;

  public Set<BookDto> getAllBooks() {
    Set<BookDto> bookResponses = new HashSet<>();
    List<Book> books = bookRepository.findAll();

    books.forEach(book -> bookResponses.add(ModelConverter.getBookDto(book)));

    return bookResponses;
  }

  public Set<BookDto> search(String title, String author, String language, String genre) {
    Set<BookDto> bookDtos = new HashSet<>();
    BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

    if (!StringUtils.isEmpty(title)) {
      boolQuery.must(QueryBuilders.matchQuery(Constants.TITLE, title));
    }

    if (!StringUtils.isEmpty(author)) {
      boolQuery.must(QueryBuilders.matchQuery(Constants.AUTHORS, author));
    }

    if (!StringUtils.isEmpty(language)) {
      boolQuery.must(QueryBuilders.matchQuery(Constants.LANGUAGES, language));
    }

    if (!StringUtils.isEmpty(genre)) {
      boolQuery.must(QueryBuilders.matchQuery(Constants.GENRES, genre));
    }

    SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(boolQuery).build();

    Iterable<BookEs> iterableBooks = bookEsRepository.search(searchQuery);
    iterableBooks.forEach(bookEs -> bookDtos.add(ModelConverter.getBookDto(bookEs)));

    return bookDtos;
  }

  public Set<BookDto> getRecentBooks() {
    Set<BookDto> bookDtos = new HashSet<>();
    SearchQuery searchQuery = new NativeSearchQueryBuilder()
        .withPageable(PageRequest.of(0, Constants.RECENt_LIMIT))
        .withSort(SortBuilders.fieldSort(Constants.CREATION_DATE)
            .order(SortOrder.DESC)).build();

    Iterable<BookEs> iterableBooks = bookEsRepository.search(searchQuery);
    iterableBooks.forEach(bookEs -> bookDtos.add(ModelConverter.getBookDto(bookEs)));
    return bookDtos;
  }

  public Book createBook(BookRequest bookRequest) {
    Book book = new Book();
    book.setIsbn(bookRequest.getIsbn());
    book.setTitle(bookRequest.getTitle());

    bookRequest.getAuthors().forEach(fullName -> {
      Author author = authorRepository.findByFullName(fullName)
          .orElseThrow(() -> new ResourceNotFoundException("Author", "fullName", fullName));
      book.addAuthor(author);
    });

    book.setGenres(bookRequest.getGenres());
    book.setLanguages(bookRequest.getLanguages());
    book.setPublishedDate(bookRequest.getPublishedDate());

    Book savedBook = bookRepository.save(book);
    BookEs bookEs = ModelConverter.getBookEs(savedBook);
    bookEs.setCreationDate(new Date().getTime());

    bookEsRepository.save(bookEs);

    return savedBook;
  }

  public User addToFavorite(Long bookId, Long userId) {
    Book book = bookRepository.findById(bookId)
        .orElseThrow(() -> new ResourceNotFoundException("Book", "Id", bookId));

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    user.addToFavorites(book);

    return userRepository.save(user);
  }
}