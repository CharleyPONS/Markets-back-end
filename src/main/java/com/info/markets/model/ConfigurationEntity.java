package com.info.markets.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "configuration")
public class ConfigurationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "markets", nullable = false)
    @OneToMany
    private List<MarketConfigurationEntity> markets;

    @Column(name = "ticker", nullable = false)
    @OneToMany
    private List<TickersConfigurationEntity> ticker;
}
