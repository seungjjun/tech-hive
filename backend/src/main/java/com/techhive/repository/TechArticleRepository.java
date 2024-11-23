package com.techhive.repository;

import com.techhive.entity.TechArticleEntity;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TechArticleRepository extends JpaRepository<TechArticleEntity, Long> {

//    @Query(value = "SELECT * FROM tech_articles WHERE MATCH(title, summary) AGAINST (:query IN NATURAL LANGUAGE MODE)", nativeQuery = true)
//    List<TechArticleEntity> searchByFullText(@Param("query") String query);

    List<TechArticleEntity> findAllByCompanyId(Long companyId, Sort sort);

    int countByCompanyId(Long companyId);

    List<TechArticleEntity> findTop5ByOrderByViewCountDesc();
}
