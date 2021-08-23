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
    @PutMapping("/update/{postId}")
    public Result updatePost(@PathVariable int postId,@RequestBody Post post){
        return postService.updatePost(postId,post);
    }


    @PreAuthorize("hasAnyRole('Company')")
    @GetMapping("/getAll")
    public Result getAllPostsOfCompany(){
        return postService.getPostsOfCompany();
    }


    @PreAuthorize("hasAnyRole('Company')")
    @DeleteMapping("/delete/{postId}")
    public Result deletePost(@PathVariable int postId){
        return postService.deletePost(postId);
    }

    @GetMapping("/select/{postId}")
    public Result selectPost(@PathVariable int postId){
        return postService.selectPost(postId);
    }
}
