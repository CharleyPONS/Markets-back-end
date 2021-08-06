package com.info.markets.sevice.tickers;

import com.info.markets.model.configuration.TickersConfigurationEntity;

import java.util.List;

public interface TickersConfigurationService {
    TickersConfigurationEntity findTicketByFunctionalName(String functionalName);
    List<TickersConfigurationEntity> findAllTickers();
}
