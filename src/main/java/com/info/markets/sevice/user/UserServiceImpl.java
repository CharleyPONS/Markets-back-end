package com.info.markets.sevice.user;

import com.info.markets.model.user.UserEntity;
import com.info.markets.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public UserEntity findUserByName(String username) throws UsernameNotFoundException{
        return userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }

    @Override
    public List<UserEntity> findAllUser(){
      return userRepository.findAll();
    }

    @Override
    public Boolean existsByMail(String email) {
        return userRepository.existsByMail(email);
    }

    @Override
    public Boolean existsByUserName(String username) {
        return userRepository.existsByUserName(username);
    }
}
