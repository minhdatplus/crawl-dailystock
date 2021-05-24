package com.example.crawlprice.repository;

import com.example.crawlprice.repository.entity.Capital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapitalRepository  extends JpaRepository<Capital, Long> {
}
