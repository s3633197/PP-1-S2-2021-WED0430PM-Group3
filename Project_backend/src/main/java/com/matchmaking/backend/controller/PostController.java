package com.matchmaking.backend.controller;

import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.entity.Post;
import com.matchmaking.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/post")
public class PostController {


    @Autowired
    PostService postService;

    @PreAuthorize("hasAnyRole('Company')")
    @PostMapping("/create")
    public Result createPost(@RequestBody Post post){
        return postService.createPost(post);
    }


    @GetMapping("/all")
    public Result getAllPosts(){
        return postService.getAllPosts();
    }


    @PreAuthorize("hasAnyRole('Company')")
    @PutMapping("/update/{id}")
    public Result updatePost(@PathVariable int id,@RequestBody Post post){
        return postService.updatePost(id,post);
    }


    @GetMapping("/getAll/{id}")
    public Result getAllPostsOfCompany(@PathVariable int id){
        return postService.getPostsByCompanyId(id);
    }

    @PreAuthorize("hasAnyRole('Company')")
    @DeleteMapping("/delete/{id}")
    public Result deletePost(@PathVariable int id){
        return postService.deletePost(id);
    }
}
