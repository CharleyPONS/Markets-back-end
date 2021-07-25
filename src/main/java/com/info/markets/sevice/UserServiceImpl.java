package com.info.markets.sevice;

import com.info.markets.model.UserEntity;
import com.info.markets.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(UserEntity userEntity){
      userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findUser(int id) throws Exception {
     Optional<UserEntity> userEntity = userRepository.findById(id);
     if(userEntity.isEmpty()){
         throw new Exception("User not found");
     }
      return userEntity;
    }

    @Override
    public List<UserEntity> findAllUser(){
      return userRepository.findAll();
    }


}
