package com.info.markets.model.MarketStack.ticker;

import com.info.markets.model.MarketStack.shared.PaginationResponseApi;
import lombok.Data;

import java.util.List;

@Data
public class TickerInformationResponse {
    PaginationResponseApi pagination;
    List<TickerResponse> data;
}
