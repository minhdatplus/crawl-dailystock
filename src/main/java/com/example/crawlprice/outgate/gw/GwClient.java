package com.example.crawlprice.outgate.gw;

import com.example.crawlprice.outgate.gw.response.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ssiClient", url = "${url.fundamental.host}")
public interface GwClient {

    @GetMapping("${url.endpoint.defaultallstock}")
    ListLikeResp<SingleStockModel> getAllStock();

}
