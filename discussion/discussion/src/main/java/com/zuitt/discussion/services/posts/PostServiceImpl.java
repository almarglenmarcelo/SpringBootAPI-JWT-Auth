package com.zuitt.discussion.services.posts;


import com.zuitt.discussion.config.JwtToken;
import com.zuitt.discussion.model.Post;

import com.zuitt.discussion.model.User;
import com.zuitt.discussion.repositories.PostRepository;
import com.zuitt.discussion.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class PostServiceImpl implements PostService{



    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtToken jwtToken;


    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Iterable<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public ResponseEntity getPostById(int postId) {

       return new ResponseEntity<>(postRepository.findById(postId), HttpStatus.OK);
    }

//    Get All post of a specific user
    @Override
    public Iterable<Post> getMyPosts(String stringToken) {
        User author = userRepository.findByUsername(jwtToken.getUsernameFromToken(stringToken));

        return author.getPosts();
    }

    @Override
    public ResponseEntity updatePost(int postId,String token, Post post) {

        Post postForUpdating = postRepository.findById(postId).get();
        String postAuthorName = postForUpdating.getUser().getUsername();
        String authenticatedUsername = jwtToken.getUsernameFromToken(token);

        if(authenticatedUsername.equals(postAuthorName)){
            postForUpdating.setTitle(post.getTitle());
            postForUpdating.setContent(post.getContent());

            postRepository.save(postForUpdating);

            return new ResponseEntity("Post Updated Successfully! ", HttpStatus.OK);

        }else {
            return new ResponseEntity<>("You are not authorized to edit this post!", HttpStatus.UNAUTHORIZED);
        }



    }

    @Override
    public void createPost(String token, Post post) {
        User author = userRepository.findByUsername(jwtToken.getUsernameFromToken(token));

        Post newPost = new Post();
        newPost.setTitle(post.getTitle());
        newPost.setContent(post.getContent());
        newPost.setUser(author);

        postRepository.save(newPost);

    }

    @Override
    public ResponseEntity deletePost(int postId, String stringToken) {
        Post postForDeleting = postRepository.findById(postId).get();
        String postAuthorName = postForDeleting.getUser().getUsername();
        String authenticatedUsername = jwtToken.getUsernameFromToken(stringToken);

        if(authenticatedUsername.equals(postAuthorName)){
            postRepository.deleteById(postId);
            return new ResponseEntity<>("Post Deleted Successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("You are not authorized to delete this post.", HttpStatus.UNAUTHORIZED);
        }

    }




}
