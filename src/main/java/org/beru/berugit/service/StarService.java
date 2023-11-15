/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.service;

import org.beru.berugit.persistence.entity.StarEntity;
import org.beru.berugit.persistence.repository.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author brand
 */
@Service
public class StarService {
    private final StarRepository starRepository;
    
    @Autowired
    public StarService(StarRepository starRepository) {
        this.starRepository = starRepository;
    }
    public void save(StarEntity entity){
        starRepository.save(entity);
    }
}
