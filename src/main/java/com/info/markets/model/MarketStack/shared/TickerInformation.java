package com.info.markets.model.MarketStack.shared;

import lombok.Data;

@Data
public class TickerInformation {
    String name;
    String symbol;
    boolean has_intraday;
    boolean has_eod;
    String country;
}
