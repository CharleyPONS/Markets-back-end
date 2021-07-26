package com.info.markets.model.MarketStack.shared;

import lombok.Data;

@Data
public class PaginationResponseApi {
    public int limit;
    public int offset;
    public int count;
    public int total;
}
