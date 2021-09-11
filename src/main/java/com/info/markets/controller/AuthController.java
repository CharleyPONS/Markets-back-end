package com.info.markets.controller;

import com.info.markets.core.security.jwt.JwtUtils;
import com.info.markets.dto.LoginRequest;
import com.info.markets.dto.LoginResponse;
import com.info.markets.dto.SignupRequest;
import com.info.markets.model.user.UserDetailsInfo;
import com.info.markets.model.user.UserEntity;
import com.info.markets.model.user.UserRole;
import com.info.markets.model.user.UserRoleEntity;
import com.info.markets.sevice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> registerUser(@Valid @RequestBody LoginRequest loginRequest) throws ParseException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsInfo userDetails = (UserDetailsInfo) authentication.getPrincipal();

        return ResponseEntity.ok(new LoginResponse(jwt,
                userDetails.getUsername(),
                userDetails.getEmail()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.existsByUserName(signUpRequest.getUserName())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (userService.existsByMail(signUpRequest.getMail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        // Create new user's account
        UserEntity user = new UserEntity();
        List<UserRoleEntity> roles = new ArrayList<>();
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setName(UserRole.ROLE_USER);
        userRole.setUser(user);
        roles.add(userRole);
        Optional.ofNullable(signUpRequest.getFavoriteMarket()).ifPresent(user::setFavoriteMarket);
        Optional.ofNullable(signUpRequest.getFirstname()).ifPresent(user::setFirstname);
        Optional.ofNullable(signUpRequest.getLastname()).ifPresent(user::setLastname);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setMail(signUpRequest.getMail());
        user.setUserName(signUpRequest.getUserName());
        user.setRole(roles);
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }
}
