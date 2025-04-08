package com.solitude.blogapi;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post {

    public Post(String title, String content, List<String> tags, String category) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.category = category;
        this.onCreate();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String category;
    
    @ElementCollection
    private List<String> tags;

    private LocalDate createdAtDate;
    private LocalDate updatedAtDate;
    
    @PrePersist
    protected void onCreate() {
        createdAtDate = LocalDate.now();
        updatedAtDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAtDate = LocalDate.now();
    }

}
