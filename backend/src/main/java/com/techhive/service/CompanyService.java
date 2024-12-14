package com.techhive.service;

import com.techhive.api.dto.request.company.CompanySortType;
import com.techhive.api.dto.response.company.CompanyDetailResult;
import com.techhive.entity.CompanyEntity;
import com.techhive.model.jdbc.CompanyTechArticle;
import com.techhive.repository.CompanyRepository;
import com.techhive.repository.TechArticleRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final TechArticleRepository techArticleRepository;

    public List<CompanyTechArticle> getCompaniesList(CompanySortType sortType, int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        LocalDateTime currentDateTime = LocalDateTime.now();
        return switch (sortType) {
            case RECENT -> companyRepository.findAllCompaniesOrderByLatestTechArticle(pageable, currentDateTime.minusDays(7)).getContent();
            case COUNT -> companyRepository.findAllCompaniesOrderByTechArticleCount(pageable, currentDateTime.minusDays(7)).getContent();
        };
    }

    public CompanyDetailResult getCompany(Long companyId) {
        CompanyEntity companyEntity = companyRepository.findById(companyId)
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 기업 입니다. - " + companyId));
        int articleNumber = techArticleRepository.countByCompanyId(companyId);
        return CompanyDetailResult.of(companyEntity, articleNumber);
    }
}
