package com.example.BlogpostApp.controller;

import com.example.BlogpostApp.model.Post;
import com.example.BlogpostApp.model.User;
import com.example.BlogpostApp.service.IPOstService;
import com.example.BlogpostApp.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    private IPOstService postService;
    private IUserService userService;

    public PostController(IPOstService postService, IUserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Post> postPage = postService.getPosts(PageRequest.of(page, size));
        return new ResponseEntity<>(postPage.getContent(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable long id) {
        Optional<Post> post = postService.getPost(id);

        if (post.isPresent()) {
            return new ResponseEntity<>(post.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUser(
            @PathVariable long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Post> postPage = postService.getPostsByUserId(userId, PageRequest.of(page, size));

        if (postPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(postPage.getContent(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestParam long userId, @RequestBody Post post) {
        Optional<User> user = userService.getUserById(userId);

        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            post.setUser(user.get());
            Post createdPost = postService.createPost(post);

            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable long id, @RequestParam long userId, @RequestBody Post post) {
        Optional<User> user = userService.getUserById(userId);

        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<Post> existingPost = postService.getPost(id);
        if (!existingPost.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        post.setUser(user.get());

        boolean updated = postService.updatePost(id, post);

        if (!updated) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Post updatedPost = postService.getPost(id).orElse(null);

        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) {
        Optional<Post> existingPost = postService.getPost(id);

        if (!existingPost.isPresent()) {
            return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
        }

        try {
            postService.deletePost(id);
            return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while deleting the post.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
