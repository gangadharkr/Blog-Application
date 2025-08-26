package com.project.blog.controllers;

import com.project.blog.model.Comment;
import com.project.blog.payloads.ApiResponse;
import com.project.blog.payloads.CommentDto;
import com.project.blog.service.CommentService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/post/{postId}/user/{userId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId,@PathVariable Integer userId) {
    CommentDto createdComment=this.commentService.createComment(comment,postId,userId);
    return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }
    @DeleteMapping("comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully!!",true), HttpStatus.CREATED);
    }

}
