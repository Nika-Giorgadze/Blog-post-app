package com.example.BlogpostApp.service;

import com.example.BlogpostApp.model.Post;
import com.example.BlogpostApp.repository.IPostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPOstService {

    private IPostRepository iPostRepository;

    public PostService(IPostRepository postRepository) {
        this.iPostRepository = postRepository;
    }

    @Override
    public Page<Post> getPosts(Pageable pageable) {
        return iPostRepository.findAll(pageable);
    }

    @Override
    public Optional<Post> getPost(long id) {
        return iPostRepository.findById(id);
    }

    @Override
    public Post createPost(Post post) {
        return iPostRepository.save(post);
    }

    public boolean updatePost(long id, Post post) {
        Optional<Post> existingPostOpt = iPostRepository.findById(id);
        if (existingPostOpt.isPresent()) {
            Post existingPost = existingPostOpt.get();
            existingPost.setText(post.getText());
            existingPost.setUser(post.getUser());
            iPostRepository.save(existingPost);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePost(long id) {
        iPostRepository.deleteById(id);

        return true;
    }

    @Override
    public Page<Post> getPostsByUserId(long userId, Pageable pageable) {
        return iPostRepository.findByUserId(userId, pageable);
    }
}
