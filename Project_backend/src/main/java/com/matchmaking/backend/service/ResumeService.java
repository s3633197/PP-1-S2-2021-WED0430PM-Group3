package com.matchmaking.backend.service;

import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.entity.Account;
import com.matchmaking.backend.entity.Resume;
import com.matchmaking.backend.mapper.AccountMapper;
import com.matchmaking.backend.mapper.ResumeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ResumeService {
    @Autowired
    ResumeMapper resumeMapper;

    @Autowired
    AccountMapper accountMapper;


    private Account currentAccount(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account currentAccount = accountMapper.findAccountByEmail(email);
        return  currentAccount;
    }

    public Result createResume(Resume resume){
        if(resumeMapper.getResumeByAccountId(this.currentAccount().getAccountId()) != null){
            return Result.failed("You have already created your CV");
        }
        resume.setAccountId(this.currentAccount().getAccountId());
        resumeMapper.createResume(resume);
        return Result.success("","Successfully Created");
    }


    public Result getResume(){
        Resume resume = resumeMapper.getResumeByAccountId(this.currentAccount().getAccountId());
        if(resume == null){
            return Result.failed("Please create your CV first");
        }
        return Result.success(resumeMapper.getResumeByAccountId(this.currentAccount().getAccountId()));
    }

    public Result updateResume(Resume resume){
        // get current resume
        Resume current = resumeMapper.getResumeByAccountId(this.currentAccount().getAccountId());
        if(current == null){
            return Result.failed("Please create your CV first");
        }
        // set account id and seeker id to new Resume to update the information
        resume.setSeekerId(current.getSeekerId());
        resume.setAccountId(current.getAccountId());
        resumeMapper.updateResume(resume);
        return Result.success("Successfully updated");
    }
}
