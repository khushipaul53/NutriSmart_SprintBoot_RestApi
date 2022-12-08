package com.springboot.smartnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.smartnutri.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
