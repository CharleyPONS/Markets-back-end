package com.info.markets.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "market_configuration")
public class MarketConfigurationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "functional_name")
    private Markets functionalName;

    @Column(name = "mic")
    private String mic;

}
