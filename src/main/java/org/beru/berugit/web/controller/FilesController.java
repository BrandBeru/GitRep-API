/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.web.controller;

import org.beru.berugit.persistence.entity.FilesEntity;
import org.beru.berugit.servers.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author brand
 */
@RestController
@RequestMapping("/files")
public class FilesController {
    private final HTTP http;

    @Autowired
    public FilesController(HTTP http) {
        this.http = http;
    }
    @GetMapping
    public ResponseEntity<FilesEntity> getAll(){
        return ResponseEntity.ok(http.getAll());
    }
    
}
