package com.springboot.smartnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.smartnutri.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(long postId);
}
