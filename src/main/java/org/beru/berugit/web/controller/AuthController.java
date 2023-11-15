/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.web.controller;

import org.beru.berugit.persistence.entity.UserEntity;
import org.beru.berugit.service.UserService;
import org.beru.berugit.service.dto.LoginDto;
import org.beru.berugit.web.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author brand
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService user;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService user) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.user = user;
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(login);
        
        System.out.println(authentication.isAuthenticated());
        System.out.println(authentication.getPrincipal());
        
        String jwt = this.jwtUtil.create(loginDto.getUsername());
        
        return ResponseEntity.ok(jwt);
    }
    @GetMapping("/profile")
    public ResponseEntity<UserEntity> profile(){
        return ResponseEntity.ok(user.getProfileInfo());
    }
    
}
