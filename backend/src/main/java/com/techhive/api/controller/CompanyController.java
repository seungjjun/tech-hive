package com.techhive.api.controller;

import com.techhive.api.dto.response.company.CompanyDetailResponse;
import com.techhive.api.dto.response.company.CompanyDetailResult;
import com.techhive.api.dto.response.company.CompanyListResponse;
import com.techhive.api.dto.response.company.CompanyResponse;
import com.techhive.entity.Company;
import com.techhive.service.CompanyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public CompanyListResponse getCompanies() {
        List<Company> companiesList = companyService.getCompaniesList();
        return CompanyListResponse.from(companiesList);
    }

    @GetMapping("/{companyId}")
    public CompanyDetailResponse getCompanyDetail(@PathVariable Long companyId) {
        CompanyDetailResult result = companyService.getCompany(companyId);
        return CompanyDetailResponse.from(result);
    }
}
