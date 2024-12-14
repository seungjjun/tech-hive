package com.techhive.repository;

import com.techhive.entity.OriginTechArticleEntity;
import com.techhive.entity.OriginTechArticleStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginTechArticleRepository extends JpaRepository<OriginTechArticleEntity, Long> {
    List<OriginTechArticleEntity> findByStatus(OriginTechArticleStatus originTechArticleStatus);
}
