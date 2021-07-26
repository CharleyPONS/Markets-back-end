package com.info.markets.model.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tickers_configuration")
@NoArgsConstructor
public class TickersConfigurationEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column()
    private String symbol;

    @ManyToOne
    @JoinColumn(name = "market_id", nullable = false)
    private MarketConfigurationEntity market;

}
