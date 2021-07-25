package com.info.markets.sevice;

import com.info.markets.model.configuration.MarketConfigurationEntity;
import java.util.List;

public interface MarketConfigurationService {
    MarketConfigurationEntity findMarketByStockIndex(String functionalName);
    List<MarketConfigurationEntity> findAllMarket();
}
