package com.info.markets.model.MarketStack;

import lombok.Data;

@Data
public class StockExchange {
    String name;
    String acronym;
    String mic;
    String country;
    String country_code;
    String city;
    String website;
    TimeZoneTicker timezone;
}
