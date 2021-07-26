package com.info.markets.model.configuration;

import com.info.markets.model.MarketStack.market.Markets;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "market_configuration")
public class MarketConfigurationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Markets functionalName;

    @Column()
    private String mic;

    @OneToMany(mappedBy = "market")
    private Set<TickersConfigurationEntity> tickers;

}
