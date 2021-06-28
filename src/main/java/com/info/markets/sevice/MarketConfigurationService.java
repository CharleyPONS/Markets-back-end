package com.info.markets.sevice;

import com.info.markets.model.MarketConfigurationEntity;
import java.util.List;

public interface MarketConfigurationService {
    MarketConfigurationEntity findMarketByStockIndex(String functionalName);
    List<MarketConfigurationEntity> findAllMarket();
}
