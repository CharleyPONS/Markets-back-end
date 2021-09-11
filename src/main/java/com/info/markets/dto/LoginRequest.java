package com.info.markets.dto;

import com.info.markets.model.user.UserRoleEntity;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class LoginRequest {
    @Email
    private String mail;
    private String userName;
    @Size(max = 120)
    private String password;
    private List<UserRoleEntity> role;
}
