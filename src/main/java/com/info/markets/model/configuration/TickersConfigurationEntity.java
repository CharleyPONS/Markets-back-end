package com.info.markets.model.configuration;

import com.info.markets.model.MarketStack.Markets;
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
    private Markets functionalName;

    @Column()
    private String symbol;

}
