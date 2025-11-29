package com.example.blog_nilay.repository;


import com.example.blog_nilay.entity.Comment;
import com.example.blog_nilay.entity.CommentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBlogPostIdAndStatus(Long postId, CommentStatus status);
    List<Comment> findByStatus(CommentStatus status);
}