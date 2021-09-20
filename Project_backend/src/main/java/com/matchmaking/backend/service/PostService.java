package com.matchmaking.backend.service;


import com.matchmaking.backend.common.algorithm.RecommendAlgorithm;
import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.entity.Company;
import com.matchmaking.backend.entity.Post;
import com.matchmaking.backend.entity.Resume;
import com.matchmaking.backend.entity.Target;
import com.matchmaking.backend.entity.vo.PostListVO;
import com.matchmaking.backend.entity.vo.PostPageVO;
import com.matchmaking.backend.mapper.CompanyMapper;
import com.matchmaking.backend.mapper.PostMapper;
import com.matchmaking.backend.mapper.ResumeMapper;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    PostMapper postMapper;

    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @Autowired
    RecommendAlgorithm recommendAlgorithm;

    @Autowired
    ResumeMapper resumeMapper;


    public Result createPost(Post post){
        // 需要检查相同的post
        Company company = companyService.currentCompany();
        if(company == null){
            return Result.failed("Please upload your company information first");
        }
        post.setCompanyId(company.getCompanyId());
        postMapper.createPost(post);
        return Result.create("Successfully create");
    }


    // get all posts as post cards display in the Job list
    public Result getAllPosts(){
        List<Post> postList = postMapper.getAllPosts();

        // convert to PostVO
        if(!postList.isEmpty()){
            List<PostListVO> postVOList = postList.stream()
                    .map(e -> new PostListVO(
                            e.getPostId(),
                            e.getTitle(),
                            e.getMinSalary(),
                            e.getMaxSalary(),
                            e.getEducationalBackground(),
                            e.getAddress(),
                            e.getIndustry(),
                            e.getEmploymentType(),
                            companyMapper.selectCompany(e.getCompanyId()).getStartUpDate(),
                            companyMapper.selectCompany(e.getCompanyId()).getCompanyName(),
                            e.getCompanyId()
                    )).collect(Collectors.toList());
            return Result.success(postVOList);
        }
        return Result.failed("No posts available now");

    }

    public Result selectPost(int postId){
        Post post = postMapper.getPost(postId);
        if(post == null){
            return Result.notFound();
        }
        PostPageVO postPageVO = dozerBeanMapper.map(post,PostPageVO.class);
        Company company = companyMapper.selectCompany(post.getCompanyId());
        postPageVO.setCompanyName(company.getCompanyName());
        postPageVO.setStartUpDate(company.getStartUpDate());
        postPageVO.setOwner(company.getOwner());
        return Result.success(postPageVO);
    }


    public Result updatePost(int postId,Post post){
        Post current = postMapper.getPost(postId);
        // check the user access to update the post
        if(current.getCompanyId() != companyService.currentCompany().getCompanyId()){
            return Result.notAuthorised("Not Authorised");
        }
        if(current == null){
            return Result.failed("Can not find this post");
        }
        post.setPostId(current.getPostId());
        post.setCompanyId(current.getCompanyId());
        postMapper.updatePost(post);
        return Result.success("Successfully update");
    }

    public Result getPostsOfCompany(){
        List<Post> postList =postMapper.getPostsByCompanyId(companyService.currentCompany().getCompanyId());
        if(postList.isEmpty()) return Result.failed("No post available now");
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

    public List<Resume> getRecommendResumes(int postId){
        Company company = companyService.currentCompany();
        Post post =postMapper.getPost(postId);
        // 应该抛出找不到异常

        if(post == null){
            return null;
        }
        if(post.getCompanyId() != company.getCompanyId()){
            return null;
        }
        Target target = recommendAlgorithm.postCovertToTarget(post);
        List<Resume> resumeList = resumeMapper.getAllResume();
        return recommendAlgorithm.matchResume(target.getExpectedSalary(),target.getJobType().getValue(),target.getDegree().getValue(),resumeList);
    }
}
