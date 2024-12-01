package com.techhive.repository;

import com.techhive.entity.TechArticleEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TechArticleRepository extends JpaRepository<TechArticleEntity, Long> {

    @Query(value = "SELECT * " +
                    "FROM tech_articles " +
                    "WHERE MATCH(title, core_summary, one_line_summary, three_line_summary) " +
                    "AGAINST (:query IN NATURAL LANGUAGE MODE)",
        nativeQuery = true)
    List<TechArticleEntity> searchByFullText(@Param("query") String query);

    List<TechArticleEntity> findAllByCompanyId(Long companyId, Sort sort);

    int countByCompanyId(Long companyId);

    List<TechArticleEntity> findTop3ByOrderByViewCountDesc();

    @Query(value = "SELECT * FROM tech_articles ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<TechArticleEntity> findRandomTechArticles(@Param("limit") int limit);

    List<TechArticleEntity> findByOrderByPublishedDateDesc(Pageable pageable);
}
