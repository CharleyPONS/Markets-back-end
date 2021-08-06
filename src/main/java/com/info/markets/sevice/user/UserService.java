package com.info.markets.sevice.user;

import com.info.markets.model.user.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(UserEntity userEntity);

    Optional<UserEntity> findUser(int id) throws Exception;

    UserEntity findUserByName(String username);

    List<UserEntity> findAllUser();
}
