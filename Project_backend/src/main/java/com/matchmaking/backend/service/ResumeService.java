package com.matchmaking.backend.service;

import com.matchmaking.backend.common.algorithm.RecommendAlgorithm;
import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.entity.Post;
import com.matchmaking.backend.entity.Resume;
import com.matchmaking.backend.entity.Target;
import com.matchmaking.backend.entity.vo.ResumeVO;
import com.matchmaking.backend.mapper.PostMapper;
import com.matchmaking.backend.mapper.ResumeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeService {
    @Autowired
    ResumeMapper resumeMapper;

    @Autowired
    AccountService accountService;

    @Autowired
    PostMapper postMapper;

    @Autowired
    RecommendAlgorithm recommendAlgorithm;


    public Result createResume(Resume resume){
        if(resumeMapper.getResumeByAccountId(accountService.currentAccount().getAccountId()) != null){
            return Result.failed("You have already created your CV");
        }
        resume.setAccountId(accountService.currentAccount().getAccountId());
        resumeMapper.createResume(resume);
        return Result.create("","Successfully Created");
    }

    public Result getResume(){
        Resume resume = resumeMapper.getResumeByAccountId(accountService.currentAccount().getAccountId());
        if(resume == null){
            return Result.failed("Please create your CV first");
        }
        return Result.success(resumeMapper.getResumeByAccountId(accountService.currentAccount().getAccountId()));
    }

    public Resume getCurrentResume(){
      return resumeMapper.getResumeByAccountId(accountService.currentAccount().getAccountId());
    }

    public Result updateResume(Resume resume){
        // get current resume
        Resume current = resumeMapper.getResumeByAccountId(accountService.currentAccount().getAccountId());
        if(current == null){
            return Result.failed("Please create your CV first");
        }
        // set account id and seeker id to new Resume to update the information
        resume.setAccountId(current.getAccountId());
        resume.setSeekerId(current.getSeekerId());
        resumeMapper.updateResume(resume);
        return Result.success("Successfully updated");
    }

    public Result getAllResume(){
        List<Resume> resumeList = resumeMapper.getAllResume();
        List<ResumeVO> resumeVOList = resumeList.stream().map(
                e->new ResumeVO(
                 e.getSeekerId(),
                 e.getFirstName(),
                 e.getLastName(),
                 e.getGender(),
                 e.getPhone(),
                 e.getDateOfBirth(),
                 e.getSchoolName(),
                 e.getMajor(),
                 e.getEducationalBackground(),
                 e.getSkill(),
                 e.getStatement(),
                 e.getLocation(),
                 e.getWantedIndustry(),
                 e.getJobType(),
                 e.getExpectedSalary()
                )).collect(Collectors.toList());
        return Result.success(resumeVOList);
    }

    public List<Post> getAllRecommendPost(){
        Resume resume = getCurrentResume();
        if(resume == null){
            return null;
        }
        Target target = recommendAlgorithm.resumeCovertToTarget(getCurrentResume());
        List<Post> postList = postMapper.getAllPosts();
        return recommendAlgorithm.matchPost(target.getExpectedSalary(),target.getJobType().getValue(),target.getDegree().getValue(),postList);
    }

}
