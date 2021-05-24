package com.example.crawlprice.repository;

import com.example.crawlprice.repository.entity.CashDividend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashDividendRepository  extends JpaRepository<CashDividend, Long> {
}
