package com.example.BlogpostApp.service;

import com.example.BlogpostApp.model.Comment;
import com.example.BlogpostApp.model.Post;
import com.example.BlogpostApp.model.User;
import com.example.BlogpostApp.repository.ICommentRepository;
import com.example.BlogpostApp.repository.IPostRepository;
import com.example.BlogpostApp.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Comment createComment(Long postId, Long userId, Comment comment) {
        // Get the Post and User entities based on IDs
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Set the Post and User for the Comment
        comment.setPost(post);
        comment.setAuthor(user);

        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long id, Comment comment) {
        if (commentRepository.existsById(id)) {
            comment.setId(id);
            return commentRepository.save(comment);
        }
        throw new RuntimeException("Comment not found");
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Page<Comment> getCommentsByPostId(Long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable);
    }
}
