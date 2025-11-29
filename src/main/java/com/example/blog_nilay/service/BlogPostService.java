package com.example.blog_nilay.service;

import com.example.blog_nilay.entity.BlogPost;
import com.example.blog_nilay.entity.Role;
import com.example.blog_nilay.entity.User;
import com.example.blog_nilay.repository.BlogPostRepository;
import com.example.blog_nilay.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository postRepository;
    @Autowired
    private UserRepository userRepository; 

    public BlogPost createPost(BlogPost post, User author) {
        post.setUser(author); 
        post.setLikes(0);
        return postRepository.save(post);
    }

    public List<BlogPost> getAllPosts() {
        return postRepository.findAll();
    }

    public List<BlogPost> searchPosts(String keyword) {
        return postRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public List<BlogPost> searchByTag(String tag) {
        return postRepository.findByTagsContaining(tag);
    }

    public void likePost(Long postId) {
        BlogPost post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);
    }

    public BlogPost createPost(BlogPost post, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        // THE SIMPLE MANUAL CHECK
        if (user.getRole() != Role.AUTHOR && user.getRole() != Role.ADMIN) {
            throw new RuntimeException("Permission Denied: Only Authors can post!");
        }

        post.setUser(user);
        return postRepository.save(post);
    }

    public void deletePost(Long postId, Long userId) {
        User admin = userRepository.findById(userId).orElseThrow();

        // THE SIMPLE MANUAL CHECK
        if (admin.getRole() != Role.ADMIN) {
            throw new RuntimeException("Permission Denied: Only Admins can delete!");
        }

        postRepository.deleteById(postId);
    }
}
