package com.example.parazitik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    private final BlogService blogService = new BlogService(blogRepository);

    @GetMapping("/blog")
    public List<Blog> getBlogs() {
        return blogService.getAllBlogs();
    }

    @PostMapping("/blog")
    public Blog addBlog(@RequestBody Blog blog) {
        return blogService.addBlog(blog);
    }

    @PutMapping("/blog/{blogId}")
    public Blog updateBlog(@PathVariable Long blogId, @RequestBody Blog blogRequest) {
        Blog blog = blogService.getById(blogId);
        blog.setUpdated_at(new Date());
        blog.setName(blogRequest.getName());
        blog.setDescription(blogRequest.getDescription());
        blog.setDate(blogRequest.getDate());
        blog.setCategory(blogRequest.getCategory());
        return blogService.updateBlog(blog);
    }

    @DeleteMapping("blog/{blogId}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long blogId) {
        blogService.deleteBlog(blogService.getById(blogId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
