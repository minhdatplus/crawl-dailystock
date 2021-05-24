package com.example.crawlprice.repository;

import com.example.crawlprice.repository.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long>  {
}
