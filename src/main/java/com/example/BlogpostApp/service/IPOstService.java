package com.example.BlogpostApp.service;

import com.example.BlogpostApp.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPOstService {

    Page<Post> getPosts(Pageable pageable);

    Optional<Post> getPost(long id);

    Post createPost(Post post);

    boolean updatePost(long id, Post post);

    boolean deletePost(long id);

    Page<Post> getPostsByUserId(long userId, Pageable pageable);
}
