package com.info.markets.model.MarketStack.shared;

import lombok.Data;

import java.util.Date;

@Data
public class VolumeMarketTickerData {
    public Date date;
    public String symbol;
    public String exchange;
    public long open;
    public long high;
    public long low;
    public long close;
    public long volume;
}
