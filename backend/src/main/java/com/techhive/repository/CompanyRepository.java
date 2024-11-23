package com.techhive.repository;

import com.techhive.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    CompanyEntity findByNameIgnoreCase(String name);
}
