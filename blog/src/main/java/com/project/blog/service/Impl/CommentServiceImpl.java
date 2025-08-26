package com.project.blog.service.Impl;

import com.project.blog.exceptions.ResourceNotFoundException;
import com.project.blog.model.Comment;
import com.project.blog.model.Post;
import com.project.blog.model.User;
import com.project.blog.payloads.CommentDto;
import com.project.blog.payloads.PostDto;
import com.project.blog.repository.CommentRepo;
import com.project.blog.repository.PostRepo;
import com.project.blog.repository.UserRepo;
import com.project.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user id",userId));
        Comment comment=this.modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        Comment savedComment=this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
    Comment com=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","comment id",commentId));
    this.commentRepo.delete(com);
    }
}
