package com.example.crawlprice.outgate.graph.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DailyStockWrapper {

    @JsonProperty("dataList")
    private List<DailyStockPriceModel> dataList;

}
