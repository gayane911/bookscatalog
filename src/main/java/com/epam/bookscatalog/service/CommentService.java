package com.epam.bookscatalog.service;

import com.epam.bookscatalog.model.Comment;
import com.epam.bookscatalog.model.Rating;
import com.epam.bookscatalog.model.RatingIdentity;
import com.epam.bookscatalog.model.RatingName;
import com.epam.bookscatalog.payload.CommentRequest;
import com.epam.bookscatalog.payload.RatingRequest;
import com.epam.bookscatalog.repository.CommentRepository;
import com.epam.bookscatalog.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

  @Autowired
  private CommentRepository commentRepository;

  public Comment createComment(CommentRequest commentRequest, Long userId) {
    Comment comment = new Comment();
    comment.setUserId(userId);
    comment.setBookId(commentRequest.getBookId());
    comment.setText(commentRequest.getText());
    commentRepository.save(comment);

    return comment;
  }


}
