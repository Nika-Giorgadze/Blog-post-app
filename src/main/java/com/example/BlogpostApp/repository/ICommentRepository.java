package com.example.BlogpostApp.repository;

import com.example.BlogpostApp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
