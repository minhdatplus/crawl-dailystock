package com.example.crawlprice.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public interface SubCrawlService {

    CompletableFuture<Void> crawlStockDailyFromSsiGraph();

}
