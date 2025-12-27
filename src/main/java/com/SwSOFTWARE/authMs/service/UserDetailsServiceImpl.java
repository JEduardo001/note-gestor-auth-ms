package com.SwSOFTWARE.authMs.service;

import com.SwSOFTWARE.authMs.entity.AuthEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthService authService;

    public UserDetailsServiceImpl(AuthService authService){
        this.authService = authService;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        AuthEntity auth = authService.getUserByUserName(username);

        List<GrantedAuthority> roles = auth.getRoles().stream().
                map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());

        return User.builder()
                .username(auth.getUsername())
                .password(auth.getPassword())
                .roles(String.valueOf(roles))
                .build();

    }

}
