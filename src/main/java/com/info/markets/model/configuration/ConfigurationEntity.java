package com.info.markets.model.configuration;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "configuration")
public class ConfigurationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
}
