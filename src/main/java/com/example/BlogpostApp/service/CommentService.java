package com.example.BlogpostApp.service;

import com.example.BlogpostApp.exceptions.ResourceNotFoundException;
import com.example.BlogpostApp.model.Comment;
import com.example.BlogpostApp.model.Post;
import com.example.BlogpostApp.model.User;
import com.example.BlogpostApp.repository.ICommentRepository;
import com.example.BlogpostApp.repository.IPostRepository;
import com.example.BlogpostApp.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {
    private final ICommentRepository commentRepository;
    private final IPostRepository postRepository;
    private final IUserRepository userRepository; // Add user repository

    // Inject the user repository into the constructor
    public CommentService(ICommentRepository commentRepository, IPostRepository postRepository, IUserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository; // Initialize user repository
    }

    @Override
    public Comment createComment(Long postId, Long userId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with ID: " + postId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        comment.setPost(post);
        comment.setUser(user);
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long id, Comment comment) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        existingComment.setText(comment.getText());
        return commentRepository.save(existingComment);
    }

    @Override
    public void deleteComment(Long id) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with ID: " + id));
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}
