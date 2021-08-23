package com.matchmaking.backend.controller;


import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.entity.Company;
import com.matchmaking.backend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
public class CompanyController {


    @Autowired
    CompanyService companyService;

    @PreAuthorize("hasAnyRole('Company')")
    @PostMapping("/create")
    public Result createCompany(@RequestBody Company company){
        return companyService.createCompany(company);
    }

    @PreAuthorize("hasAnyRole('Company')")
    @GetMapping("/info")
    public Result getCompany(){
        return companyService.getCompanyInfo();
    }


    @GetMapping("/all")
    public Result getAllCompany(){
        return companyService.getAllCompany();
    }

    @PreAuthorize("hasAnyRole('Company')")
    @PutMapping("/update")
    public Result updateCompanyInfo(@RequestBody Company company){
        return companyService.updateCompany(company);
    }

    @GetMapping("/select/{companyId}")
    public Result selectCompany(@PathVariable int companyId){
        if(companyService.selectCompany(companyId)==null){
            return Result.notFound();
        }
        return Result.success(companyService.selectCompany(companyId));
    }


}