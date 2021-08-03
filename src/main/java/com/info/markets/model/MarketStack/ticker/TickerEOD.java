package com.info.markets.model.MarketStack.ticker;

import com.info.markets.model.MarketStack.shared.PaginationResponseApi;
import lombok.Data;

@Data
public class TickerEOD {
    PaginationResponseApi pagination;
    TickerEODInformation data;
}
