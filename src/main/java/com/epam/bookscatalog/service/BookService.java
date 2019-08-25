package com.epam.bookscatalog.service;

import com.epam.bookscatalog.exception.ResourceNotFoundException;
import com.epam.bookscatalog.model.Author;
import com.epam.bookscatalog.model.Book;
import com.epam.bookscatalog.model.User;
import com.epam.bookscatalog.payload.BookRequest;
import com.epam.bookscatalog.payload.BookResponse;
import com.epam.bookscatalog.repository.AuthorRepository;
import com.epam.bookscatalog.repository.BookRepository;
import com.epam.bookscatalog.repository.UserRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private AuthorRepository authorRepository;

  @Autowired
  private UserRepository userRepository;

  /*@Autowired
  private VoteRepository voteRepository;

  @Autowired
  private UserRepository userRepository;*/

  private static final Logger logger = LoggerFactory.getLogger(BookService.class);

  public Set<BookResponse> getAllBooks() {
    Set<BookResponse> bookResponses = new HashSet<>();
    List<Book> books = bookRepository.findAll();

    books.forEach(book -> {
      bookResponses.add(book.getBookResponse());
    });

    return bookResponses;
  }

  /*public PagedResponse<BookResponse> getPollsCreatedBy(String username, UserPrincipal currentUser,
      int page, int size) {
    validatePageNumberAndSize(page, size);

    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

    // Retrieve all polls created by the given username
    Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
    Page<Poll> polls = pollRepository.findByCreatedBy(user.getId(), pageable);

    if (polls.getNumberOfElements() == 0) {
      return new PagedResponse<>(Collections.emptyList(), polls.getNumber(),
          polls.getSize(), polls.getTotalElements(), polls.getTotalPages(), polls.isLast());
    }

    // Map Polls to PollResponses containing vote counts and poll creator details
    List<Long> pollIds = polls.map(Poll::getId).getContent();
    Map<Long, Long> choiceVoteCountMap = getChoiceVoteCountMap(pollIds);
    Map<Long, Long> pollUserVoteMap = getPollUserVoteMap(currentUser, pollIds);

    List<BookResponse> pollResponses = polls.map(poll -> {
      return ModelMapper.mapPollToPollResponse(poll,
          choiceVoteCountMap,
          user,
          pollUserVoteMap == null ? null : pollUserVoteMap.getOrDefault(poll.getId(), null));
    }).getContent();

    return new PagedResponse<>(pollResponses, polls.getNumber(),
        polls.getSize(), polls.getTotalElements(), polls.getTotalPages(), polls.isLast());
  }*/

  /*public PagedResponse<BookResponse> getPollsVotedBy(String username, UserPrincipal currentUser,
      int page, int size) {
    validatePageNumberAndSize(page, size);

    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

    // Retrieve all pollIds in which the given username has voted
    Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
    Page<Long> userVotedPollIds = voteRepository.findVotedPollIdsByUserId(user.getId(), pageable);

    if (userVotedPollIds.getNumberOfElements() == 0) {
      return new PagedResponse<>(Collections.emptyList(), userVotedPollIds.getNumber(),
          userVotedPollIds.getSize(), userVotedPollIds.getTotalElements(),
          userVotedPollIds.getTotalPages(), userVotedPollIds.isLast());
    }

    // Retrieve all poll details from the voted pollIds.
    List<Long> pollIds = userVotedPollIds.getContent();

    Sort sort = new Sort(Sort.Direction.DESC, "createdAt");
    List<Poll> polls = pollRepository.findByIdIn(pollIds, sort);

    // Map Polls to PollResponses containing vote counts and poll creator details
    Map<Long, Long> choiceVoteCountMap = getChoiceVoteCountMap(pollIds);
    Map<Long, Long> pollUserVoteMap = getPollUserVoteMap(currentUser, pollIds);
    Map<Long, User> creatorMap = getPollCreatorMap(polls);

    List<BookResponse> pollResponses = polls.stream().map(poll -> {
      return ModelMapper.mapPollToPollResponse(poll,
          choiceVoteCountMap,
          creatorMap.get(poll.getCreatedBy()),
          pollUserVoteMap == null ? null : pollUserVoteMap.getOrDefault(poll.getId(), null));
    }).collect(Collectors.toList());

    return new PagedResponse<>(pollResponses, userVotedPollIds.getNumber(),
        userVotedPollIds.getSize(), userVotedPollIds.getTotalElements(),
        userVotedPollIds.getTotalPages(), userVotedPollIds.isLast());
  }*/


  public Book createBook(BookRequest bookRequest) {
    Book book = new Book();
    /*poll.setQuestion(bookRequest.getQuestion());

    bookRequest.getChoices().forEach(choiceRequest -> {
      poll.addChoice(new Choice(choiceRequest.getText()));
    });

    Instant now = Instant.now();
    Instant expirationDateTime = now.plus(Duration.ofDays(bookRequest.getPollLength().getDays()))
        .plus(Duration.ofHours(bookRequest.getPollLength().getHours()));

    poll.setExpirationDateTime(expirationDateTime);*/

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

    return bookRepository.save(book);
  }

  public User addToFavorite(Long bookId, Long userId) {
    Book book = bookRepository.findById(bookId)
        .orElseThrow(() -> new ResourceNotFoundException("Book", "Id", bookId));

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    user.addToFavorites(book);

    return userRepository.save(user);
  }

  /*public BookResponse getPollById(Long pollId, UserPrincipal currentUser) {
    Poll poll = pollRepository.findById(pollId).orElseThrow(
        () -> new ResourceNotFoundException("Poll", "id", pollId));

    // Retrieve Vote Counts of every choice belonging to the current poll
    List<ChoiceVoteCount> votes = voteRepository.countByPollIdGroupByChoiceId(pollId);

    Map<Long, Long> choiceVotesMap = votes.stream()
        .collect(Collectors.toMap(ChoiceVoteCount::getChoiceId, ChoiceVoteCount::getVoteCount));

    // Retrieve poll creator details
    User creator = userRepository.findById(poll.getCreatedBy())
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", poll.getCreatedBy()));

    // Retrieve vote done by logged in user
    Vote userVote = null;
    if (currentUser != null) {
      userVote = voteRepository.findByUserIdAndPollId(currentUser.getId(), pollId);
    }

    return ModelMapper.mapPollToPollResponse(poll, choiceVotesMap,
        creator, userVote != null ? userVote.getChoice().getId() : null);
  }

  public BookResponse castVoteAndGetUpdatedPoll(Long pollId, VoteRequest voteRequest,
      UserPrincipal currentUser) {
    Poll poll = pollRepository.findById(pollId)
        .orElseThrow(() -> new ResourceNotFoundException("Poll", "id", pollId));

    if (poll.getExpirationDateTime().isBefore(Instant.now())) {
      throw new BadRequestException("Sorry! This Poll has already expired");
    }

    User user = userRepository.getOne(currentUser.getId());

    Choice selectedChoice = poll.getChoices().stream()
        .filter(choice -> choice.getId().equals(voteRequest.getChoiceId()))
        .findFirst()
        .orElseThrow(
            () -> new ResourceNotFoundException("Choice", "id", voteRequest.getChoiceId()));

    Vote vote = new Vote();
    vote.setPoll(poll);
    vote.setUser(user);
    vote.setChoice(selectedChoice);

    try {
      vote = voteRepository.save(vote);
    } catch (DataIntegrityViolationException ex) {
      logger.info("User {} has already voted in Poll {}", currentUser.getId(), pollId);
      throw new BadRequestException("Sorry! You have already cast your vote in this poll");
    }

    //-- Vote Saved, Return the updated Poll Response now --

    // Retrieve Vote Counts of every choice belonging to the current poll
    List<ChoiceVoteCount> votes = voteRepository.countByPollIdGroupByChoiceId(pollId);

    Map<Long, Long> choiceVotesMap = votes.stream()
        .collect(Collectors.toMap(ChoiceVoteCount::getChoiceId, ChoiceVoteCount::getVoteCount));

    // Retrieve poll creator details
    User creator = userRepository.findById(poll.getCreatedBy())
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", poll.getCreatedBy()));

    return ModelMapper
        .mapPollToPollResponse(poll, choiceVotesMap, creator, vote.getChoice().getId());
  }


  private void validatePageNumberAndSize(int page, int size) {
    if (page < 0) {
      throw new BadRequestException("Page number cannot be less than zero.");
    }

    if (size > AppConstants.MAX_PAGE_SIZE) {
      throw new BadRequestException(
          "Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
    }
  }

  private Map<Long, Long> getChoiceVoteCountMap(List<Long> pollIds) {
    // Retrieve Vote Counts of every Choice belonging to the given pollIds
    List<ChoiceVoteCount> votes = voteRepository.countByPollIdInGroupByChoiceId(pollIds);

    Map<Long, Long> choiceVotesMap = votes.stream()
        .collect(Collectors.toMap(ChoiceVoteCount::getChoiceId, ChoiceVoteCount::getVoteCount));

    return choiceVotesMap;
  }

  private Map<Long, Long> getPollUserVoteMap(UserPrincipal currentUser, List<Long> pollIds) {
    // Retrieve Votes done by the logged in user to the given pollIds
    Map<Long, Long> pollUserVoteMap = null;
    if (currentUser != null) {
      List<Vote> userVotes = voteRepository.findByUserIdAndPollIdIn(currentUser.getId(), pollIds);

      pollUserVoteMap = userVotes.stream()
          .collect(
              Collectors.toMap(vote -> vote.getPoll().getId(), vote -> vote.getChoice().getId()));
    }
    return pollUserVoteMap;
  }

  Map<Long, User> getPollCreatorMap(List<Poll> polls) {
    // Get Poll Creator details of the given list of polls
    List<Long> creatorIds = polls.stream()
        .map(Poll::getCreatedBy)
        .distinct()
        .collect(Collectors.toList());

    List<User> creators = userRepository.findByIdIn(creatorIds);
    Map<Long, User> creatorMap = creators.stream()
        .collect(Collectors.toMap(User::getId, Function.identity()));

    return creatorMap;
  }*/
}