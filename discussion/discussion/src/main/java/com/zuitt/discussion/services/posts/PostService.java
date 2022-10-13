package com.zuitt.discussion.services.posts;

import com.zuitt.discussion.model.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface PostService {

    Iterable<Post> getPosts();

    void createPost(String token, Post post);

    ResponseEntity deletePost(int postId, String stringToken);

    ResponseEntity updatePost(int postId,String token, Post post);

    ResponseEntity getPostById(int postId);

    Iterable<Post> getMyPosts(String stringToken);

}
