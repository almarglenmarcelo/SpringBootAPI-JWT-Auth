package com.zuitt.discussion.controller;

import com.zuitt.discussion.model.Post;
import com.zuitt.discussion.services.posts.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//CORS - Cross Origin Resource Sharing
//Enable all cross origin requests via @CrossOrigin
@CrossOrigin
@RestController
public class PostController {

    @Autowired
    private PostService postService;


    @GetMapping("/posts")
    public Iterable<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Object> getPost(@PathVariable int postId){
        return postService.getPostById(postId);
    }


    @PostMapping("/posts")
    public ResponseEntity<Object> createPost (@RequestHeader(value="Authorization") String token, @RequestBody Post post){
        postService.createPost(token, post);
        return new ResponseEntity<>("Post Created Successfully", HttpStatus.CREATED);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity updatePost(@PathVariable int postId,@RequestHeader(value="Authorization") String token, @RequestBody Post thePost){
        return postService.updatePost(postId, token, thePost);
    }


    @DeleteMapping("/posts/{postId}")
    public ResponseEntity deletePost(@PathVariable int postId, @RequestHeader(value="Authorization") String stringToken){
        return postService.deletePost(postId, stringToken);
    }



//    Retrieving posts for a particular user
//    http://localhost:8080/posts/{userId}/myPosts
    @GetMapping("/myPosts")
    public ResponseEntity<Object> getPosts(@RequestHeader(value="Authorization") String stringToken){
        return new ResponseEntity<>(postService.getMyPosts(stringToken), HttpStatus.OK);
    }





}
