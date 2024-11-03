package com.example.BlogpostApp.repository;

import com.example.BlogpostApp.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p")
    List<Post> getPosts();

    @Query("SELECT p FROM Post p WHERE p.user.id = :userId")
    Page<Post> findByUserId(long userId, Pageable pageable);
}
