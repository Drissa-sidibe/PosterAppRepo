package com.saraya.PosterApp.Controller;

import com.saraya.PosterApp.DTO.PostDto;
import com.saraya.PosterApp.Entity.Post;
import com.saraya.PosterApp.Excepition.PostNotFoundException;
import com.saraya.PosterApp.Service.PostServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class Controller {


    @Autowired
    private ModelMapper modelMapper;


    private final PostServiceImpl postService;

    public Controller(PostServiceImpl postService) {
        this.postService = postService;
    }
    @GetMapping
    public List<PostDto> getAllPosts(){
      return postService.getAllPosts().stream().
                map(pos ->modelMapper.map(pos, PostDto.class)).collect(Collectors.toList());
    }
    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long id  ) throws PostNotFoundException {
        Post post = postService.getPostById(id);// To get we convert Entity to DTO
        //Converting entity to DTo
        PostDto postResponse = modelMapper.map(post, PostDto.class);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<PostDto> createPosts(@RequestBody PostDto postDto){
         //convert DTO to entity
        Post postRequest =
                modelMapper.map(postDto, Post.class);
        Post post = postService.createPost(postRequest);
        // convert entity to DTO
        PostDto postResponse = modelMapper.map(post, PostDto.class);

        return new ResponseEntity<>(postResponse,HttpStatus.CREATED);

    }
    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id")Long id,
                                              @RequestBody PostDto postDto) throws PostNotFoundException {
        //DTO to Entity
        Post postRequest = modelMapper.map(postDto, Post.class);

       Post post = postService.updatePost(id, postRequest); //(updating by using serivce)
       // Entity to DTo
        PostDto postResponse = modelMapper.map(post, PostDto.class);
        return new ResponseEntity<>(postResponse, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("id")
    public void ddeletePost(@PathVariable("id") Long id) throws PostNotFoundException {
       postService.deletePost(id);

//    public ResponseEntity<PostDto> deletePost(@PathVariable("id") Long id) throws PostNotFoundException {
//        postService.deletePost(id);
//        return new ResponseEntity<>(postService, HttpStatus.OK);
    }
}
