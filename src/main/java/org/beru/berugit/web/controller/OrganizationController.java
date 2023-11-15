/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package org.beru.berugit.web.controller;

import org.beru.berugit.persistence.entity.OrganizationEntity;
import org.beru.berugit.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/org")
public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }
    @GetMapping
    public ResponseEntity<OrganizationEntity> get(){
        return ResponseEntity.ok(organizationService.getByUsername().orElse(null));
    }
    @PostMapping
    public ResponseEntity<OrganizationEntity> save(@RequestBody OrganizationEntity entity){
        return ResponseEntity.ok(organizationService.save(entity));
    }
}
