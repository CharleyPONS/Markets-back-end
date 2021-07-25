package com.info.markets.model;

import lombok.Data;

import javax.persistence.*;

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

}
