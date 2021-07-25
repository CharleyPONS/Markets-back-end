package com.info.markets.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity extends Auditable<UserEntity> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column()
    private String firstname;

    @Column()
    private String lastname;

    @Column(nullable = false)
    @Email
    private String email;

    @Column()
    private String favoriteMarket;

    @Column()
    private String age;

}
