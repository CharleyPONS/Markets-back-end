package com.info.markets.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tickers_configuration")
public class TickersConfigurationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "functionalName")
    private Markets functionalName;

    @Column(name = "symbol")
    private String symbol;

}
