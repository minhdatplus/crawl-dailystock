package com.example.crawlprice.schedulers;

import com.example.crawlprice.service.SubCrawlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class DailyStockCrawlSchedule {

    private final SubCrawlService subCrawlSsiService;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


    public DailyStockCrawlSchedule(
            SubCrawlService subCrawlService
    ) {
        this.subCrawlSsiService = subCrawlService;
    }

    @Scheduled(cron = "${crawl.cron}")
    public void crawlEach12Hour() {
        LocalDateTime now = LocalDateTime.now();
        log.info("Start crawl dailyStock at {} ", dtf.format(now));
        subCrawlSsiService.crawlStockDailyFromSsiGraph().join();
        log.info("Done crawl dailyStock at {} ", dtf.format(now));
    }

}
