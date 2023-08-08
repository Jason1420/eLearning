package com.elearning.repository.security;

import com.elearning.entity.login.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String roleName);

    Set<RoleEntity> findOneByName(String roleName);
}
