package com.example.crawlprice.repository;

import com.example.crawlprice.repository.entity.CompanyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyProfileRepository  extends JpaRepository<CompanyProfile, Long> {
}
