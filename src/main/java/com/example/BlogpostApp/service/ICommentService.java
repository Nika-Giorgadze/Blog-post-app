package com.example.BlogpostApp.service;

import com.example.BlogpostApp.model.Comment;

import java.util.List;

public interface ICommentService {
    Comment createComment(Long postId, Long userId, Comment comment);

    Comment updateComment(Long id, Comment comment);

    void deleteComment(Long id);

    List<Comment> getCommentsByPostId(Long postId);
}
