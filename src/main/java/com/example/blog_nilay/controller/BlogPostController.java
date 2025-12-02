package com.example.blog_nilay.controller;


import com.example.blog_nilay.entity.BlogPost;
import com.example.blog_nilay.entity.User;
import com.example.blog_nilay.repository.UserRepository;
import com.example.blog_nilay.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private UserRepository userRepository; 


    // URL: POST /api/posts?userId=1
    @PostMapping
    public BlogPost createPost(@RequestBody BlogPost post, @RequestParam Long userId) {
        User author = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return blogPostService.createPost(post, author);
    }

    // URL: GET /api/posts/search?q=Spring
    @GetMapping("/search")
    public List<BlogPost> searchPosts(@RequestParam String q) {
        return blogPostService.searchPosts(q);
    }

    // URL: POST /api/posts/1/like
    @PostMapping("/{id}/like")
    public void likePost(@PathVariable Long id) {
        blogPostService.likePost(id);
    }

    @GetMapping
    public List<BlogPost> getAllPosts() {
        return blogPostService.getAllPosts();
    }
}