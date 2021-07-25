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
    private int id;
}
