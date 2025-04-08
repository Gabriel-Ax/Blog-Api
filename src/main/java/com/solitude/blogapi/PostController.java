package com.solitude.blogapi;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    PostController(PostRepository postRepository, PostMapper postMapper) {

        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDto postData) {

        Post post =  postMapper.dtoToPost(postData);
        postRepository.save(post);

        return ResponseEntity.ok(post);
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable(name = "id") Long id) {

        Optional<Post> possiblePost = postRepository.findById(id);
        return possiblePost.orElse(new Post());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable(name = "id") Long id) {

        Optional<Post> possiblePost = postRepository.findById(id);
        if (possiblePost.isPresent()) {postRepository.delete(possiblePost.orElse(null));}
        return new ResponseEntity<Post>(HttpStatusCode.valueOf(200));
    }


    @PostMapping("/{id}")
    public ResponseEntity<Post> updatePostById(@PathVariable(name = "id") Long id, @RequestBody PostDto postData) {

        Optional<Post> possiblePost = postRepository.findById(id);
        Post oldPost = possiblePost.orElse(null);

        oldPost.setTitle(postData.getTitle());
        oldPost.setCategory(postData.getCategory());
        oldPost.setContent(postData.getContent());
        oldPost.setTags(postData.getTags());

        Post updatedPost = postRepository.save(oldPost);

        return ResponseEntity.ok(updatedPost);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {

        List<Post> allPosts = postRepository.findAll();
        return new ResponseEntity<List<Post>>(allPosts, HttpStatus.OK);

    }

    @GetMapping("/term") 
    public ResponseEntity<List<Post>> getPostsByTag(@RequestParam String tag) {
        List<Post> posts = postRepository.getAllByTag(tag);
        return ResponseEntity.ok(posts);
    }

}   
