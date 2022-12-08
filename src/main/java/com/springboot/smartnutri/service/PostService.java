package com.springboot.smartnutri.service;

import java.util.List;

import com.springboot.smartnutri.payload.PostDto;
import com.springboot.smartnutri.payload.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}
