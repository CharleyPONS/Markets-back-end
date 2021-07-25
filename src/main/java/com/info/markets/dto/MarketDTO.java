package com.info.markets.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MarketDTO {
    public Date date;
    public String exchange;
    public long open;
    public long high;
    public long low;
    public long close;
    public long volume;
}
