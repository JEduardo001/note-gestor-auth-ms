package com.SwSOFTWARE.authMs.service;

import com.SwSOFTWARE.authMs.dto.auth.DtoAuth;
import com.SwSOFTWARE.authMs.dto.auth.DtoCreateAuth;
import com.SwSOFTWARE.authMs.dto.auth.DtoUpdateAuth;
import com.SwSOFTWARE.authMs.entity.AuthEntity;
import com.SwSOFTWARE.authMs.entity.RoleEntity;
import com.SwSOFTWARE.authMs.exception.auth.PasswordsDoNotMatchException;
import com.SwSOFTWARE.authMs.exception.auth.AuthEmailAlreadyInUseException;
import com.SwSOFTWARE.authMs.exception.auth.AuthNotFoundException;
import com.SwSOFTWARE.authMs.exception.auth.AuthUsernameAlreadyInUseException;
import com.SwSOFTWARE.authMs.mapper.AuthMapper;
import com.SwSOFTWARE.authMs.repository.AuthRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

    public List<DtoAuth> getAllAuth(Integer page, Integer size){
        Pageable s = PageRequest.of(page,size);
        return authRepository.findAll(s).map(authMapper::toDto).getContent();
    }

    public AuthEntity getUserByUserName(String username){
        return authRepository.findByUsername(username).orElseThrow(AuthNotFoundException::new);
    }

    public AuthEntity getAuthById(Long id){
        return authRepository.findById(id).orElseThrow(AuthNotFoundException::new);
    }

    public DtoAuth getAuth(Long idAuth){
        return authMapper.toDto(authRepository.findById(idAuth).orElseThrow(AuthNotFoundException::new));
    }


    public DtoAuth createUser(DtoCreateAuth request){

            if(!request.password().equals(request.passwordRepeat())){
                throw new PasswordsDoNotMatchException();
            }

            if(authRepository.existsByUsername(request.username())){
                throw new AuthUsernameAlreadyInUseException();
            }

            if(authRepository.existsByEmail(request.email())){
                throw new AuthEmailAlreadyInUseException();
            }

            List<RoleEntity> roles = roleService.getEntityRolesByIds(request.idRoles());

            AuthEntity auth = AuthEntity.builder()
                    .username(request.username())
                    .password(passwordEncoder.encode(request.password()))
                    .email(request.email())
                    .active(request.active())
                    .createdAt(LocalDateTime.now())
                    .disabledAt( (request.active()) ? LocalDateTime.now() : null )
                    .roles(roles)
                    .build();

            authRepository.save(auth);

            return authMapper.toDto(auth);
    }

    public DtoAuth updateAuth(DtoUpdateAuth request){

        AuthEntity auth = getAuthById(request.id());

        if(authRepository.existsByUsernameAndIdNot(request.username(),request.id())){
            throw new AuthUsernameAlreadyInUseException();
        }

        if(authRepository.existsByEmailAndIdNot(request.email(),request.id())){
            throw new AuthEmailAlreadyInUseException();
        }

        List<RoleEntity> roles = roleService.getEntityRolesByIds(request.idRoles());

        auth.setUsername(request.username());
        auth.setEmail(request.email());
        auth.setActive(request.active());
        auth.setDisabledAt( (request.active()) ? null : LocalDateTime.now());
        auth.setRoles(roles);

        return authMapper.toDto(authRepository.save(auth));
    }
}
