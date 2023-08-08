package com.elearning.repository.security;

import com.elearning.entity.login.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    UserEntity findOneById(Long id);

    UserEntity findOneByUsername(String username);
}
