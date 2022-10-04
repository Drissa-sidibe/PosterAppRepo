package com.saraya.PosterApp.Service;

import com.saraya.PosterApp.Entity.Post;
import com.saraya.PosterApp.Excepition.PostNotFoundException;

import java.util.List;

public interface PostServiceInterface {
    List<Post> getAllPosts();
    Post createPost(Post post);
    Post updatePost(Long id, Post post) throws PostNotFoundException;
    void deletePost(long id) throws PostNotFoundException;
    Post getPostById(Long id) throws PostNotFoundException;
}
