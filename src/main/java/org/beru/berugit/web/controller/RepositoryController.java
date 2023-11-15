/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.web.controller;

import java.util.List;
import org.beru.berugit.persistence.entity.RepositoryEntity;
import org.beru.berugit.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/repositories")
public class RepositoryController {
    private final RepositoryService repositoryService;

    @Autowired
    public RepositoryController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }
    
    @GetMapping
    public ResponseEntity<List<RepositoryEntity>> getAll(){
        return ResponseEntity.ok(repositoryService.getAll());
    }
    @GetMapping("/{name}")
    public ResponseEntity<RepositoryEntity> getByName(@PathVariable String name){
        RepositoryEntity entity = repositoryService.getByName(name).get();
        if(entity == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(entity);
    }
    @PostMapping
    public ResponseEntity<RepositoryEntity> save(@RequestBody RepositoryEntity repositoryEntity){
        return ResponseEntity.ok(repositoryService.save(repositoryEntity));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<RepositoryEntity> delete(@PathVariable int id){
        RepositoryEntity entity = repositoryService.remove(id).get();
        if(entity==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(entity);
    }
}
