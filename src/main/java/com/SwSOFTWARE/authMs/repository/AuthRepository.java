package com.SwSOFTWARE.authMs.repository;

import com.SwSOFTWARE.authMs.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<AuthEntity,Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<AuthEntity> findByUsername(String username);
}
