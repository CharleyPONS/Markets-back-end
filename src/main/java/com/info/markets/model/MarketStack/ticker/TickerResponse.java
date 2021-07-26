package com.info.markets.model.MarketStack.ticker;

import com.info.markets.model.MarketStack.shared.StockExchange;
import lombok.Data;

@Data
public class TickerResponse {
    String name;
    String symbol;
    StockExchange stock_exchange;
}
