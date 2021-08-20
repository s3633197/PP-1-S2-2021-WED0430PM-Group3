package com.matchmaking.backend.service;


import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.entity.Company;
import com.matchmaking.backend.entity.Post;
import com.matchmaking.backend.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostMapper postMapper;

    @Autowired
    CompanyService companyService;

    public Result createPost(Post post){
        Company company = companyService.currentCompany();
        if(company == null){
            return Result.failed("Please upload your company information first");
        }
        post.setCompanyId(company.getCompanyId());
        postMapper.createPost(post);
        return Result.create("Successfully create");
    }


    public Result getAllPosts(){
        return Result.success(postMapper.getAllPosts());
    }

    // 需要检查操作用户是否为内容的所有者
    public Result updatePost(int postId,Post post){
        Post current = postMapper.getPost(postId);
        if(current == null){
            return Result.failed("Can not find this post");
        }
        post.setPostId(current.getPostId());
        postMapper.updatePost(post);
        return Result.success("Successfully update");
    }

    public Result getPostsByCompanyId(int companyId){

        List<Post> postList =postMapper.getPostsByCompanyId(companyId);
        return Result.success(postList);
    }

    public Result deletePost(int postId){
        Company company = companyService.currentCompany();
        Post post = postMapper.getPost(postId);
        if(post == null){
            return Result.failed("Not Found");
        }
        if(post.getCompanyId() != company.getCompanyId()){
            return Result.notAuthorised("Not Authorised");
        }

        postMapper.deletePost(postId);
        return Result.success("Successfully deleted");
    }
}
