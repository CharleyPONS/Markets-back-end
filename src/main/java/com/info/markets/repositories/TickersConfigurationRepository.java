package com.info.markets.repositories;

import com.info.markets.model.MarketConfigurationEntity;
import com.info.markets.model.TickersConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface TickersConfigurationRepository extends JpaRepository<TickersConfigurationEntity, Integer> {
    @Query(
            value = "SELECT * FROM tickers_configuration tc WHERE tc.functional_name = :functionalName",
            nativeQuery = true
    )
    @Transactional
    TickersConfigurationEntity findTickersByFunctionalName(@Param("functionalName") String functionalName);
}
