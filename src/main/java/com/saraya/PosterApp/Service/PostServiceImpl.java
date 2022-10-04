package com.saraya.PosterApp.Service;

import com.saraya.PosterApp.DTO.PostDto;
import com.saraya.PosterApp.Entity.Post;
import com.saraya.PosterApp.Excepition.PostNotFoundException;
import com.saraya.PosterApp.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostServiceInterface{

    @Autowired
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long id, Post post) throws PostNotFoundException {
        Post post1 = postRepository.findById(id).orElseThrow(()->
                new PostNotFoundException(id));
        post1.setId(post.getId());
        post1.setDescription(post.getDescription());
        post1.setContent(post.getContent());
        return postRepository.save(post);
    }

    @Override
    public void deletePost(long id) throws PostNotFoundException {
        Post post = postRepository.findById(id).orElseThrow(()->
                new PostNotFoundException(id));
        postRepository.delete(getPostById(id));
    }

    @Override
    public Post getPostById(Long id) throws PostNotFoundException {
        Optional<Post> result = postRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }else {
            throw new PostNotFoundException(id);
        }
        //THis is just another way of doing it
//        Post post = postRepository.findById(id).orElseThrow(() ->
//                new PostNotFoundException(id));
//        return post;

    }
public PostDto convertDtoToEntity(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;

    }
}
