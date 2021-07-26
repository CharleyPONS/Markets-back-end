package com.info.markets.model.MarketStack.ticker;

import com.info.markets.model.MarketStack.shared.PaginationResponseApi;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TickerEOD {
    PaginationResponseApi pagination;
    TickerEODInformation data;
}
