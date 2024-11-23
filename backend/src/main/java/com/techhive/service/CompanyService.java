package com.techhive.service;

import com.techhive.api.dto.response.company.CompanyDetailResult;
import com.techhive.entity.CompanyEntity;
import com.techhive.repository.CompanyRepository;
import com.techhive.repository.TechArticleRepository;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final TechArticleRepository techArticleRepository;

    public List<CompanyEntity> getCompaniesList() {
        return companyRepository.findAll();
    }

    public CompanyDetailResult getCompany(Long companyId) {
        CompanyEntity companyEntity = companyRepository.findById(companyId)
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 기업 입니다. - " + companyId));
        int articleNumber = techArticleRepository.countByCompanyId(companyId);
        return CompanyDetailResult.of(companyEntity, articleNumber);
    }
}
