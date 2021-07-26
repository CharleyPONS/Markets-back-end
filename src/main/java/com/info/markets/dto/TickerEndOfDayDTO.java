package com.info.markets.dto;

import lombok.Data;

import java.util.List;

@Data
public class TickerEndOfDayDTO {
    String name;
    String symbol;
    List<VolumeMarketTickerDataDTO> eod;
}
