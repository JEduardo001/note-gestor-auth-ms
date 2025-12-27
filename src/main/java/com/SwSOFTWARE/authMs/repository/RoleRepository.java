package com.SwSOFTWARE.authMs.repository;

import com.SwSOFTWARE.authMs.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name,Long id);
}
