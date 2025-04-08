package com.solitude.blogapi;

import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    
    public Post dtoToPost(PostDto postDto) {

        Post post = new Post(postDto.getTitle(), postDto.getContent(), postDto.getTags(), postDto.getCategory());
        return post;
    }
}
