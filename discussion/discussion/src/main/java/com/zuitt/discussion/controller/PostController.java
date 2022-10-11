package com.zuitt.discussion.controller;

import com.zuitt.discussion.model.Post;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {


    //    Retrieving all posts
//    http://localhost:8080/api/posts
    @RequestMapping( value="/posts", method= RequestMethod.GET)
    public String getPosts() {
        return "All posts retrieved!";
    }

    //   Create a post
    @RequestMapping(value="/posts", method=RequestMethod.POST)
    public String createPost(){
        return "Post Created";
    }


    //  Get a specific Post
    @RequestMapping(value="/posts/{postId}", method=RequestMethod.GET)
    public String getPost(@PathVariable int postId){
        return "Viewing Details of post " + postId ;
    }


    //  Delete a Specific Post
    @RequestMapping(value="/posts/{postId}", method=RequestMethod.DELETE)
    public String deletePost(@PathVariable int postId){
        return "The post " + postId + " has been deleted" ;
    }



    //    Updating a Post
    @RequestMapping(value="/posts/{postId}", method=RequestMethod.PUT)
    public Post updatePost(@PathVariable int postId, @RequestBody Post thePost){
        return thePost;
    }


    //    Retrieving posts for a particular user
//    http://localhost:8080/posts/{userId}/myPosts
    @GetMapping("/myPosts")
    public String getPostsFromUser(@RequestHeader(value="Authorization") String user){
        return "Posts for " + user + " have been retrieved.";
    }



}
