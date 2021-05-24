package com.example.crawlprice.repository;

import com.example.crawlprice.repository.entity.DailyStockPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyStockPriceRepository extends JpaRepository<DailyStockPrice, Long> {
}
