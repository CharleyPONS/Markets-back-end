package com.info.markets.model.MarketStack;

import lombok.Data;

@Data
public class TickerResponse {
    PaginationResponseApi pagination;
    TickerResponseApiData data;
}
