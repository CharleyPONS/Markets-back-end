package com.info.markets.model;

import lombok.Data;

import java.util.Date;

@Data
public class MarketResponseApiData {
    public Date date;
    public String symbol;
    public String exchange;
    public long open;
    public long high;
    public long low;
    public long close;
    public long volume;
    public Double adj_high;
    public Double adj_close;
    public Double adj_open;
    public Double adj_low;
    public Double adj_volume;
    public Double split_factor;
}
