package com.zuitt.discussion.services;

import com.zuitt.discussion.model.Post;

import java.util.Optional;

public interface PostService {

    public Iterable<Post> findAll();
    public Optional<Post> findById(int postId);
    public void savePost(Post post);
    public void deletePost(int postId);
}
