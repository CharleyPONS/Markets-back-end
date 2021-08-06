package com.info.markets.sevice.user;

import com.info.markets.model.user.UserDetailsInfo;
import com.info.markets.model.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsInfoImpl implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public UserDetailsInfoImpl(UserService userService) {
    this.userService = userService;
    }

    @Override
    public UserDetailsInfo loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userService.findUserByName(username);
        return UserDetailsInfo.buildUserDetails(user);
    }
}
