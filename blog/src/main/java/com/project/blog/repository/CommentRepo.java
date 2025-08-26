package com.project.blog.repository;

import com.project.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository <Comment,Integer> {
}
