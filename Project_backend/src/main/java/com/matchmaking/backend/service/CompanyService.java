package com.matchmaking.backend.service;


import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.entity.Account;
import com.matchmaking.backend.entity.Company;
import com.matchmaking.backend.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    AccountService accountService;


    public Result createCompany(Company company){
        Account account = accountService.currentAccount();
        if(companyMapper.getCompany(account.getAccountId()) != null) {
            return Result.failed("You have already upload your Company information");
        }
        company.setAccountId(accountService.currentAccount().getAccountId());
        companyMapper.createCompany(company);
        return Result.create("","Successfully created");
    }

    public Result getCompany(){
        Account account = accountService.currentAccount();
        Company company = companyMapper.getCompany(account.getAccountId());
        if(company == null){
            return Result.failed("Please upload your company information first");
        }
            return Result.success(company);
    }


    public Result getAllCompany(){
        List<Company> companies = companyMapper.getAllCompany();
        if(companies.isEmpty()){
            return Result.failed("No company available now");
        }
        return Result.success(companies);
    }

    public Result updateCompany(Company company){
        Company current = companyMapper.getCompany(accountService.currentAccount().getAccountId());
        if( current == null){
            return Result.failed("Please upload your company information first");
        }
        company.setCompanyId(current.getCompanyId());
        companyMapper.updateCompanyInfo(company);
        return Result.success("Successfully updated");

    }
}
