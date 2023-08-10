package com.elearning.service.security;

import com.elearning.entity.login.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CustomUserDetailServiceImpl implements UserDetailsService {
    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = accountService.loadUserByUsername(username);
        if (userEntity == null) throw new BadCredentialsException(String.format("User %s not found", username));
        List<SimpleGrantedAuthority> authorities = userEntity.getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());
        UserDetails userDetails = new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
//        if (!userEntity.isEnabled()) throw new Exception403("Unconfirmed account");
        return userDetails;
    }

    public boolean checkUserId(Long id) {
        UserEntity user = accountService.loadUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());
        Long studentID = (user.getStudent() == null) ? 0 : user.getStudent().getId();
        Long teacherID = (user.getTeacher() == null) ? 0 : user.getTeacher().getId();
        Long userId = studentID + teacherID;
        return userId == id || SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().toString().contains("ADMIN");
    }
}
