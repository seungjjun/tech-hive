package com.techhive.repository;

import com.techhive.entity.TechArticle;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TechArticleRepository extends JpaRepository<TechArticle, Long> {

    List<TechArticle> findAllByCompanyId(Long companyId, Sort sort);

    int countByCompanyId(Long companyId);

    List<TechArticle> findTop5ByOrderByViewCountDesc();
}
