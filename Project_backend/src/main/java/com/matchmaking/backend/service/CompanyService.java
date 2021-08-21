package com.matchmaking.backend.service;


import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.entity.Account;
import com.matchmaking.backend.entity.Company;
import com.matchmaking.backend.entity.Post;
import com.matchmaking.backend.entity.vo.CompanyVO;
import com.matchmaking.backend.entity.vo.PostListVO;
import com.matchmaking.backend.mapper.CompanyMapper;
import com.matchmaking.backend.mapper.PostMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    AccountService accountService;

    @Autowired
    PostMapper postMapper;

    @Autowired
    protected Mapper dozerMapper;

    public Result createCompany(Company company){
        Account account = accountService.currentAccount();
        if(companyMapper.getCompany(account.getAccountId()) != null) {
            return Result.failed("You have already upload your Company information");
        }
        company.setAccountId(accountService.currentAccount().getAccountId());
        companyMapper.createCompany(company);
        return Result.create("","Successfully created");
    }

    // only allow company to get their info
    public Result getCompanyInfo(){
        Company company = currentCompany();
        if(company == null){
            return Result.failed("Please upload your company information first");
        }
            return Result.success(company);
    }

    // return all company info in the database
    public Result getAllCompany(){
        List<Company> companies = companyMapper.getAllCompany();
        if(companies.isEmpty()){
            return Result.failed("No company available now");
        }
        return Result.success(companies);
    }

    public Result updateCompany(Company company){
        Company current = this.currentCompany();
        if( current == null){
            return Result.failed("Please upload your company information first");
        }
        company.setAccountId(current.getAccountId());
        company.setCompanyId(current.getCompanyId());
        companyMapper.updateCompanyInfo(company);
        return Result.success("Successfully updated");
    }


    // locate the user company info
    public Company currentCompany(){
        Account account = accountService.currentAccount();
        return companyMapper.getCompany(account.getAccountId());
    }


    // allow user to view the company info and their posts
    public CompanyVO selectCompany(int companyId){
        Company company = companyMapper.selectCompany(companyId);
        if(company == null){
            return null;
        }
        List<Post> postList = postMapper.getPostsByCompanyId(company.getCompanyId());
        // convert to Company VO
        CompanyVO companyVO = dozerMapper.map(company, CompanyVO.class);
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
        companyVO.setPosts(postVOList);
        return companyVO;
    }


}
