package com.zuitt.discussion.services.posts;


import com.zuitt.discussion.model.Post;
import com.zuitt.discussion.repositories.PostRepository;
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

    @Override
    public ResponseEntity updatePost(int postId, Post post) {
        Post postForUpdating = postRepository.findById(postId).get();

        postForUpdating.setTitle(post.getTitle());
        postForUpdating.setContent(post.getContent());

        postRepository.save(postForUpdating);

        return new ResponseEntity("Post Updated Successfully! ", HttpStatus.OK);
    }

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public ResponseEntity deletePost(int postId) {
        postRepository.deleteById(postId);
        return new ResponseEntity<>("Post Deleted Successfully", HttpStatus.OK);
    }
}
