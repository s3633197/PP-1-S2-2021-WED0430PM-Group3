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


    // create resume if user haven't created
    public Result createResume(Resume resume){
        if(resumeMapper.getResumeByAccountId(accountService.currentAccount().getAccountId()) != null){
            return Result.failed("You have already created your CV");
        }
        resume.setAccountId(accountService.currentAccount().getAccountId());
        resumeMapper.createResume(resume);
        return Result.create("","Successfully Created");
    }

    // get resume of user
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

    // show all resume to company role user
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

    // get recommendPost base on job seeker's experience
    public List<Post> getAllRecommendPost(){
        Resume resume = getCurrentResume();
        if(resume == null){
            return null;
        }
        Target target = recommendAlgorithm.resumeCovertToTarget(getCurrentResume());
        // get all post as target for match
        List<Post> postList = postMapper.getAllPosts();
        // represent job type, educational background to number
        return recommendAlgorithm.matchPost(target.getJobType().getValue(),target.getDegree().getValue(),
                resume.getLocation(),resume.getWantedIndustry(),
                postList);
    }

    // check user have resume or not
    public Result  checkResume(){
        Resume resume = getCurrentResume();
        if(resume==null){
            return Result.failed(false);
        }
        return Result.success(true);
    }
}
