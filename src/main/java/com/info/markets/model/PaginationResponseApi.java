package com.info.markets.model;

import lombok.Data;

@Data
public class PaginationResponseApi {
    public int limit;
    public int offset;
    public int count;
    public int total;
}
