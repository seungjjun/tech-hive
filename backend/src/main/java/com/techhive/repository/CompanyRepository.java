package com.techhive.repository;

import com.techhive.entity.CompanyEntity;
import com.techhive.model.jdbc.CompanyTechArticle;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    @Query(
        "SELECT " +
            "new com.techhive.model.jdbc.CompanyTechArticle(" +
                "c.id, " +
                "c.name, " +
                "COUNT(ta), " +
                "MAX(ta.publishedDate), " +
                "CASE WHEN COUNT(CASE WHEN ta.publishedDate >= :threshold THEN 1 END) > 0 THEN true ELSE false END" +
            ") " +
        "FROM CompanyEntity c " +
        "LEFT JOIN TechArticleEntity ta ON ta.company = c " +
        "GROUP BY c.id, c.name " +
        "ORDER BY MAX(ta.publishedDate) DESC")
    Page<CompanyTechArticle> findAllCompaniesOrderByLatestTechArticle(Pageable pageable,
                                                                      @Param("threshold") LocalDateTime threshold);

    @Query(
        "SELECT " +
            "new com.techhive.model.jdbc.CompanyTechArticle(" +
                "c.id, " +
                "c.name, " +
                "COUNT(ta), " +
                "MAX(ta.publishedDate), " +
                "CASE WHEN COUNT(CASE WHEN ta.publishedDate >= :threshold THEN 1 END) > 0 THEN true ELSE false END" +
            ") " +
        "FROM CompanyEntity c " +
        "JOIN TechArticleEntity ta ON ta.company = c " +
        "GROUP BY c.id " +
        "ORDER BY COUNT(ta.id) DESC"
    )
    Page<CompanyTechArticle> findAllCompaniesOrderByTechArticleCount(Pageable pageable,
                                                                     @Param("threshold") LocalDateTime threshold);
}
