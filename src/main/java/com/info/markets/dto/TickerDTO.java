package com.info.markets.dto;

import com.info.markets.model.MarketStack.shared.StockExchange;
import lombok.Data;

@Data
public class TickerDTO {
    String name;
    String symbol;
    StockExchange stock_exchange;
}
