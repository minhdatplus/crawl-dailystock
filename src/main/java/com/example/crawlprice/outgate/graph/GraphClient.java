package com.example.crawlprice.outgate.graph;

import com.example.crawlprice.outgate.graph.request.DailyStockReqModel;
import com.example.crawlprice.outgate.graph.response.DailyStockResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "clientGraph", url = "${url.gw.host}")
public interface GraphClient {

    @PostMapping("${url.gw.endpoint.stockPrice}")
    DailyStockResp getDailyStockData(@RequestBody DailyStockReqModel dailyStockReqModel);

}
