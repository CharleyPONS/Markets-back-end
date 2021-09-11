package com.info.markets.model.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Column()
    private boolean has_eod;

    @Column()
    private boolean has_intraday;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "market_id")
    private MarketConfigurationEntity market;

}
