package com.techhive.repository;

import com.techhive.entity.TechArticleEntity;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TechArticleRepository extends JpaRepository<TechArticleEntity, Long> {

    Page<TechArticleEntity> findAllByCompanyId(Long companyId, Pageable pageable);

    int countByCompanyId(Long companyId);

    @Modifying
    @Query("UPDATE TechArticleEntity a SET a.viewCount = a.viewCount + 1 WHERE a.id = :id")
    void incrementViewCount(@Param("id") Long articleId);

    List<TechArticleEntity> findTop5ByOrderByViewCountDesc();

    @Query(value = "SELECT * FROM tech_articles ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<TechArticleEntity> findRandomTechArticles(@Param("limit") int limit);


    @Query(value = "SELECT * " +
        "FROM tech_articles " +
        "WHERE company_id = :companyId " +
        "  AND id != :techArticleId " +
        "ORDER BY RAND() " +
        "LIMIT :limit",
        nativeQuery = true)
    List<TechArticleEntity> findRandomTechArticlesByCompanyId(@Param("limit") int limit,
                                                              @Param("techArticleId") Long articleId,
                                                              @Param("companyId") Long companyId);

    List<TechArticleEntity> findByIdIn(List<Long> techArticleIds, Sort sort);
}
