package com.saraya.PosterApp.DTO;

import com.saraya.PosterApp.Entity.Post;
import lombok.Data;

@Data
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;


    public Post converEntityToDto(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        return post;
    }
}