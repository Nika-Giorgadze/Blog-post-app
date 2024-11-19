package com.example.BlogpostApp.service;

import com.example.BlogpostApp.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPOstService {
    Post createPost(Post post);

    Post updatePost(Long id, Post post);

    void deletePost(Long id);

    Page<Post> getAllPosts(Pageable pageable);
}
