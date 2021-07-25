package com.info.markets.sevice;

import com.info.markets.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(UserEntity userEntity);
    Optional<UserEntity> findUser(int id) throws Exception;
    List<UserEntity> findAllUser();
}
