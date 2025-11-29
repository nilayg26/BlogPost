package com.example.blog_nilay.service;


import com.example.blog_nilay.entity.*;
import com.example.blog_nilay.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogPostRepository postRepository;

    @Autowired
    private UserRepository userRepository;
    public Comment addComment(Long postId, Long userId, String content) {
        BlogPost post = postRepository.findById(postId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        Comment comment = new Comment();
        comment.setCommentBody(content);
        comment.setBlogPost(post);
        comment.setUser(user);
        comment.setStatus(CommentStatus.PENDING);

        return commentRepository.save(comment);
    }
    public void approveComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        comment.setStatus(CommentStatus.APPROVED);
        commentRepository.save(comment);
    }
    public List<Comment> getCommentsForPost(Long postId) {
        return commentRepository.findByBlogPostIdAndStatus(postId, CommentStatus.APPROVED);
    }
}
