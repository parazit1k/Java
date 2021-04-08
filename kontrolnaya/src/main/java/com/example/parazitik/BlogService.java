package com.example.parazitik;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
    
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Blog getById(Long id) {
        return blogRepository.getOne(id);
    }

    public Blog addBlog(Blog blog) {
        return blogRepository.saveAndFlush(blog);
    }

    public Blog updateBlog(Blog blog) {
        return blogRepository.saveAndFlush(blog);
    }

    public void deleteBlog(Blog blog) {
        blogRepository.delete(blog);
    }
}
