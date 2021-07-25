package com.info.markets.sevice;

import com.info.markets.model.TickersConfigurationEntity;

import java.util.List;

public interface TickersConfigurationService {
    TickersConfigurationEntity findTicketByFunctionalName(String functionalName);
    List<TickersConfigurationEntity> findAllTickers();
}
