package com.example.blog_nilay.service;


import com.example.blog_nilay.entity.BlogPost;
import com.example.blog_nilay.entity.Role;
import com.example.blog_nilay.entity.User;
import com.example.blog_nilay.repository.BlogPostRepository;
import com.example.blog_nilay.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlogPostServiceTest {

    @Mock
    private BlogPostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BlogPostService blogPostService;

    // --- TEST CASE 1: Successful Post Creation ---
    @Test
    void shouldCreatePost_WhenUserIsAuthor() {
        User author = new User();
        author.setId(1L);
        author.setRole(Role.AUTHOR); 

        BlogPost newPost = new BlogPost();
        newPost.setTitle("Test Post");
        when(userRepository.findById(1L)).thenReturn(Optional.of(author));
        when(postRepository.save(any(BlogPost.class))).thenReturn(newPost);
        BlogPost created = blogPostService.createPost(newPost, 1L);
        assertNotNull(created);
        assertEquals("Test Post", created.getTitle());
    }

    // --- TEST CASE 2: Security Check Failure ---
    @Test
    void shouldThrowError_WhenUserIsReader() {
        User reader = new User();
        reader.setId(2L);
        reader.setRole(Role.READER); 

        BlogPost newPost = new BlogPost();

        when(userRepository.findById(2L)).thenReturn(Optional.of(reader));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            blogPostService.createPost(newPost, 2L);
        });

        assertEquals("Permission Denied: Only Authors/Admins can post!", exception.getMessage());
    }

    // --- TEST CASE 3: Search Functionality ---
    @Test
    void shouldReturnPosts_WhenSearching() {

        BlogPost p1 = new BlogPost();
        p1.setTitle("Java Basics");
        
        when(postRepository.findByTitleContainingIgnoreCase("Java"))
            .thenReturn(Arrays.asList(p1));


        List<BlogPost> results = blogPostService.searchPosts("Java");


        assertEquals(1, results.size());
        assertEquals("Java Basics", results.get(0).getTitle());
    }
}