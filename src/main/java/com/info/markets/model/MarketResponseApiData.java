package com.info.markets.model;

import lombok.Data;

import java.util.Date;

@Data
public class MarketResponseApiData {
    public Date date;
    public String symbol;
    public String exchange;
    public int open;
    public int high;
    public int low;
    public int close;
    public int volume;
    public Double adj_high;
    public Double adj_close;
    public Double adj_open;
    public Double adj_low;
    public Double adj_volume;
    public Double split_factor;


}
