package com.info.markets.model.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "market_configuration")
public class MarketConfigurationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String functionalName;

    @Column()
    private String mic;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TickersConfigurationEntity> tickers = new HashSet<>();

}
