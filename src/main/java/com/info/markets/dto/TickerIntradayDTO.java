package com.info.markets.dto;

import lombok.Data;

import java.util.List;

@Data
public class TickerIntradayDTO {
    String name;
    String symbol;
    List<VolumeMarketTickerDataDTO> intraday;
}
