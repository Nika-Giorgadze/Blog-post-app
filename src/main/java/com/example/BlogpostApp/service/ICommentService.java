package com.example.BlogpostApp.service;

import com.example.BlogpostApp.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICommentService {
    Comment createComment(Long postId, Long userId, Comment comment);
    Comment updateComment(Long id, Comment comment);
    void deleteComment(Long id);
    Page<Comment> getCommentsByPostId(Long postId, Pageable pageable);
}
