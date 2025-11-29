package com.example.blog_nilay.repository;


import com.example.blog_nilay.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    List<BlogPost> findByTitleContainingIgnoreCase(String keyword);
    List<BlogPost> findByTagsContaining(String tag);
    List<BlogPost> findByUserId(Long userId);
}