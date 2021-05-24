package com.example.crawlprice.service.impl;

import com.example.crawlprice.outgate.gw.GwClient;
import com.example.crawlprice.outgate.gw.response.*;
import com.example.crawlprice.outgate.graph.GraphClient;
import com.example.crawlprice.outgate.graph.request.DailyStockReqModel;
import com.example.crawlprice.outgate.graph.response.DailyStockPriceModel;
import com.example.crawlprice.outgate.graph.response.DailyStockResp;
import com.example.crawlprice.repository.DailyStockPriceRepository;
import com.example.crawlprice.repository.entity.DailyStockPrice;
import com.example.crawlprice.service.SubCrawlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class SubCrawlServiceImpl implements SubCrawlService {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private final GwClient gwClient;
    private final GraphClient ssiGraphClient;
    private final DailyStockPriceRepository dailyStockPriceRepository;

    @Autowired
    public SubCrawlServiceImpl(
            GwClient gwClient,
            GraphClient ssiGraphClient,
            DailyStockPriceRepository dailyStockPriceRepository
    ) {
        this.gwClient = gwClient;
        this.ssiGraphClient = ssiGraphClient;
        this.dailyStockPriceRepository = dailyStockPriceRepository;
    }

    private void saveDailyStockToDb(String symbol, DailyStockPriceModel dailyStockPriceModel) {
        DailyStockPrice dailyStockPrice = new DailyStockPrice();
        BeanUtils.copyProperties(dailyStockPriceModel, dailyStockPrice);
        dailyStockPrice.setSymbol(symbol);
        try {
            dailyStockPriceRepository.save(dailyStockPrice);
            log.info("Save a price of {} in {}", symbol, dailyStockPrice.getTradingDate());
        } catch (Exception e) {
            log.info("Failed to save dailyStockPrice to DB --> {} - {}", e.getMessage(), e.getCause());
        }
    }

    private void crawlAndSaveFromSsiGraph(String symbol) {
        DailyStockReqModel dailyStockReqModel = new DailyStockReqModel();

        dailyStockReqModel.setSymbol(symbol);
        dailyStockReqModel.setSize(1000000000);
        dailyStockReqModel.setFromDate(simpleDateFormat.format(new Date(System.currentTimeMillis() - 1 * 24 * 60 * 60 * 1000)));
        dailyStockReqModel.setOffset(1);
        dailyStockReqModel.setToDate(simpleDateFormat.format(new Date(System.currentTimeMillis()- 1 * 24 * 60 * 60 * 1000)));

        try {
            DailyStockResp dailyStockResp = ssiGraphClient.getDailyStockData(dailyStockReqModel);
            dailyStockResp.getData().getStockPrice().getDataList().forEach(item -> saveDailyStockToDb(symbol, item));
        } catch (Exception e) {
            log.info("Error to crawl daily stock --> {} - {}", e.getMessage(), e.getCause());
        }

    }

    @Override
    public CompletableFuture<Void> crawlStockDailyFromSsiGraph() {
        return CompletableFuture.runAsync(() -> {
            List<SingleStockModel> listSingleStock = gwClient.getAllStock().getData();
            listSingleStock.forEach(item -> crawlAndSaveFromSsiGraph(item.getCode()));
        });
    }

}
