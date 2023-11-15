/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.web.controller;

import java.util.List;
import org.beru.berugit.persistence.entity.UserEntity;
import org.beru.berugit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author brand
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }
    @GetMapping("/{username}")
    public ResponseEntity<UserEntity> getInfo(@PathVariable String username){
        UserEntity entity = userService.getUserInfo(username);
        if(entity==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(entity);
    }
    @PostMapping
    public ResponseEntity<UserEntity> save(@RequestBody UserEntity user){
        return ResponseEntity.ok(userService.save(user));
    }
    
}
