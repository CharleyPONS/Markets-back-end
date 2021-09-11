package com.info.markets.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.info.markets.model.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity extends Auditable<UserEntity> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column()
    @Nullable
    private String firstname;

    @Column()
    @Nullable
    private String lastname;

    @Column(nullable = false)
    @Email
    private String mail;

    @Column(nullable = false)
    private String userName;

    @Column()
    @Size(max = 120)
    private String password;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private List<UserRoleEntity> role;

    @Column()
    @Nullable
    private String favoriteMarket;

    @Column()
    @Nullable
    private String age;

}
