package com.info.markets.model.MarketStack.shared;

import com.info.markets.model.MarketStack.shared.VolumeMarketTickerData;
import lombok.Data;

@Data
public class EndOfDayData extends VolumeMarketTickerData {
    public Double adj_high;
    public Double adj_close;
    public Double adj_open;
    public Double adj_low;
    public Double adj_volume;
    public Double split_factor;
}
