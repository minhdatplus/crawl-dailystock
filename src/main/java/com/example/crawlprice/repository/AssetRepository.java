package com.example.crawlprice.repository;

import com.example.crawlprice.repository.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository  extends JpaRepository<Asset, Long> {
}
