package com.zuitt.discussion.services;


import com.zuitt.discussion.model.Post;
import com.zuitt.discussion.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(int postId) {
        Optional<Post> thePost = postRepository.findById(postId);

        if(thePost.isEmpty())
            throw new RuntimeException("No such Post!");

        return thePost;

    }

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void deletePost(int postId) {
        Optional<Post> thePost = postRepository.findById(postId);

        if(thePost.isEmpty())
            throw new RuntimeException("No such Post!");
        else
            postRepository.deleteById(postId);

    }
}
