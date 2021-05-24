package com.example.crawlprice.controller;


import com.example.crawlprice.factory.resfact.GeneralResponse;
import com.example.crawlprice.factory.resfact.ResponseFactory;
import com.example.crawlprice.service.SubCrawlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping(path = "/external/crawler/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class CrawlController {

    private final SubCrawlService subCrawlSsiService;

    @Autowired
    public CrawlController(
            SubCrawlService subCrawlSsiService
    ) {
        this.subCrawlSsiService = subCrawlSsiService;
    }

    @GetMapping("/crawl-daily-stock")
    public ResponseEntity<GeneralResponse<Object>> crawlDailyStockPrice() {
        subCrawlSsiService.crawlStockDailyFromSsiGraph();
        return ResponseFactory.success();
    }

}
