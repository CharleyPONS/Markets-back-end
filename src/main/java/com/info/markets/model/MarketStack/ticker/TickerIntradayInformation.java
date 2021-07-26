package com.info.markets.model.MarketStack.ticker;

import com.info.markets.model.MarketStack.shared.TickerInformation;
import com.info.markets.model.MarketStack.shared.VolumeMarketTickerData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TickerIntradayInformation extends TickerInformation {
    List<VolumeMarketTickerData> intraday;
}
