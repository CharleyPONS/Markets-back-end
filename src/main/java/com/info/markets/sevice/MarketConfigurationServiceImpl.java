package com.info.markets.sevice;

import com.info.markets.model.MarketConfigurationEntity;
import com.info.markets.repositories.MarketConfigurationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketConfigurationServiceImpl implements MarketConfigurationService{
    private MarketConfigurationRepository marketConfigurationRepository;

    public MarketConfigurationServiceImpl(MarketConfigurationRepository marketConfigurationRepository){
        this.marketConfigurationRepository = marketConfigurationRepository;
    }

    @Override
    public MarketConfigurationEntity findMarketByStockIndex(String functionalName){
     return this.marketConfigurationRepository.findMarketByStock(functionalName);
    }

    @Override
    public List<MarketConfigurationEntity> findAllMarket(){
        return this.marketConfigurationRepository.findAll();
    }

}
