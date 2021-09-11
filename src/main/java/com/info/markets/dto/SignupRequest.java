package com.info.markets.dto;

import com.info.markets.model.user.UserRoleEntity;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class SignupRequest {
    @Email
    @NotNull
    private String mail;
    @NotNull
    private String userName;
    private String lastname;
    private String firstname;
    private String favoriteMarket;
    @Size(max = 120)
    @NotNull
    private String password;
    private List<UserRoleEntity> role;
}
