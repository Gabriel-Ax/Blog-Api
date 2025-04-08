package com.solitude.blogapi;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    default public List<Post> getAllByTag(String tag) {
        return this.findAll().stream()
            .filter(post -> post.getTags().contains(tag))
            .collect(Collectors.toList());
    }
}
