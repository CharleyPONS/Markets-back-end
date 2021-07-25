package com.info.markets.repositories;

import com.info.markets.model.configuration.MarketConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MarketConfigurationRepository extends JpaRepository<MarketConfigurationEntity, Integer> {
    @Query(
            value = "SELECT * FROM market_configuration mc WHERE mc.functional_name = :functionalName",
            nativeQuery = true
    )
    @Transactional
    MarketConfigurationEntity findMarketByStock(@Param("functionalName") String functionalName);
}
