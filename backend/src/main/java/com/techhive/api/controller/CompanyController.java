package com.techhive.api.controller;

import com.techhive.api.dto.request.company.CompanySortType;
import com.techhive.api.dto.response.company.CompanyDetailResponse;
import com.techhive.api.dto.response.company.CompanyDetailResult;
import com.techhive.api.dto.response.company.CompanyListResponse;
import com.techhive.model.jdbc.CompanyTechArticle;
import com.techhive.service.CompanyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {

    private static final String DEFAULT_COMPANY_SIZE = "10";
    private static final String DEFAULT_PAGE = "1";

    private final CompanyService companyService;

    @GetMapping
    public CompanyListResponse getCompanies(
        @RequestParam(value = "sort", defaultValue = "RECENT", required = false) CompanySortType sort,
        @RequestParam(defaultValue = DEFAULT_PAGE, name = "page") int page,
        @RequestParam(value = "size", defaultValue = DEFAULT_COMPANY_SIZE, required = false) String size
    ) {
        List<CompanyTechArticle> companiesList = companyService.getCompaniesList(sort, page, Integer.parseInt(size));
        return CompanyListResponse.from(companiesList);
    }

    @GetMapping("/{companyId}")
    public CompanyDetailResponse getCompanyDetail(@PathVariable Long companyId) {
        CompanyDetailResult result = companyService.getCompany(companyId);
        return CompanyDetailResponse.from(result);
    }
}
