/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.web.controller;

import org.beru.berugit.persistence.entity.StarEntity;
import org.beru.berugit.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author brand
 */
@RestController
@RequestMapping("/star")
public class StarController {
    private final StarService starService;

    @Autowired
    public StarController(StarService starService) {
        this.starService = starService;
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody StarEntity entity){
        starService.save(entity);
        return ResponseEntity.ok().build();
    }
    
}
