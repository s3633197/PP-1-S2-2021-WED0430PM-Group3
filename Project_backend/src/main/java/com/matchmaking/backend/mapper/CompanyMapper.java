package com.matchmaking.backend.mapper;

import com.matchmaking.backend.entity.Company;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {

    void createCompany(Company company);

    Company getCompany(int accountId);

    List<Company> getAllCompany();

    Company selectCompany(int companyId);

    void updateCompanyInfo(Company company);
}
