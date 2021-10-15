package com.matchmaking.backend.controller;

import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.entity.Post;
import com.matchmaking.backend.entity.Resume;
import com.matchmaking.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/post")
public class PostController {


    @Autowired
    PostService postService;

    /**
     * All allow company role to create post
     * @param post
     * @return successful created message or error message
     */
    @PreAuthorize("hasAnyRole('Company')")
    @PostMapping("/create")
    public Result createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

    /**
     * Get all posts from database
     * @return post list
     */
    @GetMapping("/all")
    public Result getAllPosts(){
        return postService.getAllPosts();
    }


    @PreAuthorize("hasAnyRole('Company')")
    @PutMapping("/update/{postId}")
    public Result updatePost(@PathVariable int postId,@RequestBody Post post){
        return postService.updatePost(postId,post);
    }


    /**
     * Get all post of a company
     * @return post list
     */
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

    /**
     * Get post by post id
     * @param postId
     * @return Post
     */


    @GetMapping("/select/{postId}")
    public Result selectPost(@PathVariable int postId){
        return postService.selectPost(postId);
    }

    @PreAuthorize("hasAnyRole('Company')")
    @GetMapping("/recommend/all/{postId}")
    public Result getRecommendResumes(@PathVariable int postId){
        List<Resume> resumeList = postService.getRecommendResumes(postId);
        if(resumeList == null){
            return Result.notAuthorised("Not Authorised");
        }
        if(resumeList.isEmpty()){
            return Result.success("","No Recommendation");
        }
        return Result.success(resumeList);
    }
}
