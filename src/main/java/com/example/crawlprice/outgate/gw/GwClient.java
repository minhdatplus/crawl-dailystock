package com.example.crawlprice.outgate.gw;

import com.example.crawlprice.outgate.gw.response.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ssiClient", url = "${url.gw.host}")
public interface GwClient {

    @GetMapping("${url.gw.endpoint.defaultallstock}")
    ListLikeResp<SingleStockModel> getAllStock();

}
