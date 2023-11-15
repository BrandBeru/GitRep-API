/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.service;

import java.util.ArrayList;
import java.util.List;
import org.beru.berugit.persistence.entity.UserEntity;
import org.beru.berugit.persistence.entity.UserRoleEntity;
import org.beru.berugit.persistence.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author brand
 */
@Service
public class UserSecurityService implements UserDetailsService{
    private final UserRepository userRepository;

    @Autowired
    public UserSecurityService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found!", username)));
        String[] roles = userEntity.getRoles().stream().map(UserRoleEntity::getRole).toArray(String[]::new);
        return User
                .builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(grantedAuthorities(roles))
                .accountLocked(userEntity.getLocked())
                .disabled(userEntity.getDisabled())
                .build();
                
    }
    private String[] getAuthorities(String role){
        if(role.equals("ADMIN") || role.equals("USER"))
            return new String[]{""};
        return new String[]{};
    }
    private List<GrantedAuthority> grantedAuthorities(String[] roles){
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);
        for(String role : roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
//            for(String authority : this.getAuthorities(role)){
//                authorities.add(new SimpleGrantedAuthority(authority));
//            }
        }
        return authorities;
    }
}
