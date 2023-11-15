/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.beru.berugit.persistence.entity.UserEntity;
import org.beru.berugit.persistence.entity.UserRoleEntity;
import org.beru.berugit.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author brand
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final PasswordEncoder pe;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleService userRoleService, PasswordEncoder pe) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.pe = pe;
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity getUserInfo(String username) {
        UserEntity entity = userRepository.findByUsernameAndDisabledFalseAndLockedFalse(username).get();
        entity.setPassword(null);
        entity.setDisabled(null);
        entity.setLocked(null);
        return entity;
    }

    public UserEntity getProfileInfo() {
        String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UserEntity entity = userRepository.findByUsernameAndDisabledFalseAndLockedFalse(user).get();
        return entity;
    }

    public UserEntity save(UserEntity user) {
        String password = user.getPassword();
        user.setPassword(pe.encode(password));
        userRepository.save(user);
        createRole(user);

        return user;
    }

    private void createRole(UserEntity entity) {
        UserRoleEntity role = new UserRoleEntity();
        role.setRole("USER");
        role.setUsername(entity.getUsername());
        role.setGrantedDate(LocalDateTime.now());
        userRoleService.save(role);
    }
}
