package com.SwSOFTWARE.authMs.repository;

import com.SwSOFTWARE.authMs.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<AuthEntity, UUID> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsernameAndIdNot(String username,UUID id);
    boolean existsByEmailAndIdNot(String email,UUID id);
    Optional<AuthEntity> findByUsername(String username);
}
