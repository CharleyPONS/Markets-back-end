package com.info.markets.sevice;

import com.info.markets.model.configuration.TickersConfigurationEntity;
import com.info.markets.repositories.TickersConfigurationRepository;

import java.util.List;

public class TickersConfigurationServiceImpl implements TickersConfigurationService{
private TickersConfigurationRepository tickersConfigurationRepository;

public TickersConfigurationServiceImpl(TickersConfigurationRepository tickersConfigurationRepository) {
    this.tickersConfigurationRepository = tickersConfigurationRepository;
}

    @Override
    public List<TickersConfigurationEntity> findAllTickers() {
        return this.tickersConfigurationRepository.findAll();
    }

    @Override
    public TickersConfigurationEntity findTicketByFunctionalName(String functionalName) {
        return this.tickersConfigurationRepository.findTickersByFunctionalName(functionalName);
    }
}
