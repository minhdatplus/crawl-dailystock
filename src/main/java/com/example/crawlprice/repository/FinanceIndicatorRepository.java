package com.example.crawlprice.repository;


import com.example.crawlprice.repository.entity.FinanceIndicator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceIndicatorRepository extends JpaRepository<FinanceIndicator, Long> {
}
