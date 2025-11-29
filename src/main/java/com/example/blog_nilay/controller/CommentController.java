package com.example.blog_nilay.controller;



import com.example.blog_nilay.entity.Comment;
import com.example.blog_nilay.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public Comment addComment(@RequestParam Long postId, @RequestParam Long userId, @RequestBody String content) {
        return commentService.addComment(postId, userId, content);
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getComments(@PathVariable Long postId) {
        return commentService.getCommentsForPost(postId);
    }

    @PutMapping("/{commentId}/approve")
    public void approveComment(@PathVariable Long commentId) {
        commentService.approveComment(commentId);
    }
}
