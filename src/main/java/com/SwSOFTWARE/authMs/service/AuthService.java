package com.SwSOFTWARE.authMs.service;

import com.SwSOFTWARE.authMs.dto.auth.DtoAuth;
import com.SwSOFTWARE.authMs.dto.auth.DtoCreateUser;
import com.SwSOFTWARE.authMs.entity.AuthEntity;
import com.SwSOFTWARE.authMs.entity.RoleEntity;
import com.SwSOFTWARE.authMs.exception.user.PasswordsDoNotMatchException;
import com.SwSOFTWARE.authMs.exception.user.UserEmailAlreadyInUseException;
import com.SwSOFTWARE.authMs.exception.user.UserNotFoundException;
import com.SwSOFTWARE.authMs.exception.user.UserUsernameAlreadyInUseException;
import com.SwSOFTWARE.authMs.mapper.AuthMapper;
import com.SwSOFTWARE.authMs.repository.AuthRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final AuthMapper authMapper;

    public AuthService(AuthRepository authRepository,PasswordEncoder passwordEncoder,RoleService roleService,
                       AuthMapper authMapper){
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.authMapper = authMapper;
    }

    public AuthEntity getUserByUserName(String username){
        return authRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }


    public DtoAuth createUser(DtoCreateUser request){

        if(!request.password().equals(request.passwordRepeat())){
            throw new PasswordsDoNotMatchException();
        }

        if(authRepository.existsByUsername(request.username())){
            throw new UserUsernameAlreadyInUseException();
        }

        if(authRepository.existsByEmail(request.email())){
            throw new UserEmailAlreadyInUseException();
        }

        List<RoleEntity> roles = roleService.getRolesByIds(request.idRoles());

        AuthEntity auth = AuthEntity.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .email(request.email())
                .active(request.active())
                .roles(roles)
                .build();

        authRepository.save(auth);

        return authMapper.toDto(auth);
    }
}
