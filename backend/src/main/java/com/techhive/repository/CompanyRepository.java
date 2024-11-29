package com.techhive.repository;

import com.techhive.entity.CompanyEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    @Query(value = "SELECT * FROM companies ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<CompanyEntity> findRandomCompanies(@Param("limit") int limit);
}
