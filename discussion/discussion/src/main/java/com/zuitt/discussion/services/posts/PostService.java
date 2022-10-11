package com.zuitt.discussion.services.posts;

import com.zuitt.discussion.model.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface PostService {

    Iterable<Post> getPosts();

    void savePost(Post post);

    ResponseEntity deletePost(int postId);

    ResponseEntity updatePost(int postId, Post post);

    ResponseEntity getPostById(int postId);


}
