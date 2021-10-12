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

    /**
     * Only company role can create company information
     * @param company
     * @return
     */
    @PreAuthorize("hasAnyRole('Company')")
    @PostMapping("/create")
    public Result createCompany(@RequestBody Company company){
        return companyService.createCompany(company);
    }

    /**
     * Only company role can get their company information
     * @return
     */
    @PreAuthorize("hasAnyRole('Company')")
    @GetMapping("/info")
    public Result getCompany(){
        return companyService.getCompanyInfo();
    }

    /**
     * Get all company information
     * @return
     */

    @GetMapping("/all")
    public Result getAllCompany(){
        return companyService.getAllCompany();
    }

    /**
     * Update company information
     * @param company
     * @return successful message and 200 status code
     */
    @PreAuthorize("hasAnyRole('Company')")
    @PutMapping("/update")
    public Result updateCompanyInfo(@RequestBody Company company){
        return companyService.updateCompany(company);
    }

    /**
     * To show company information and all posts to user
     * @param companyId
     * @return company information and post result data
     */
    @GetMapping("/select/{companyId}")
    public Result selectCompany(@PathVariable int companyId){
        if(companyService.selectCompany(companyId)==null){
            return Result.notFound();
        }
        return Result.success(companyService.selectCompany(companyId));
    }

    /**
     * Check if company has created company information
     * @return
     */
    @PreAuthorize("hasAnyRole('Company')")
    @GetMapping("/check/info")
    public Result checkCompanyInfo(){
        return companyService.checkInformation();
    }


}
