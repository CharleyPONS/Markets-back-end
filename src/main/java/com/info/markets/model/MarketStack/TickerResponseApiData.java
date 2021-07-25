package com.info.markets.model.MarketStack;

import com.info.markets.model.MarketStack.StockExchange;
import lombok.Data;

@Data
public class TickerResponseApiData {
    String name;
    String symbol;
    StockExchange stock_exchange;
}
