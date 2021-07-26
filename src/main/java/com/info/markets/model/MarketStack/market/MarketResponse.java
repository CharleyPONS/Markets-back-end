package com.info.markets.model.MarketStack.market;

import com.info.markets.model.MarketStack.shared.EndOfDayData;
import com.info.markets.model.MarketStack.shared.PaginationResponseApi;
import lombok.Data;

import java.util.List;

@Data
public class MarketResponse {
    public PaginationResponseApi pagination;
    public List<EndOfDayData> data;
}
