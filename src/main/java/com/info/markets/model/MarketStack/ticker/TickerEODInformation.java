package com.info.markets.model.MarketStack.ticker;

import com.info.markets.model.MarketStack.shared.EndOfDayData;
import com.info.markets.model.MarketStack.shared.TickerInformation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TickerEODInformation extends TickerInformation {
    List<EndOfDayData> eod;
    }
