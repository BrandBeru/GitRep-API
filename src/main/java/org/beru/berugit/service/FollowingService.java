/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.service;

import org.beru.berugit.persistence.entity.FollowingEntity;
import org.beru.berugit.persistence.repository.FollowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author brand
 */
@Service
public class FollowingService {
    private final FollowingRepository followingRepository;

    @Autowired
    public FollowingService(FollowingRepository followingRepository) {
        this.followingRepository = followingRepository;
    }
    
    public void save(FollowingEntity entity){
        System.out.println(entity.getFollowingUsername());
        followingRepository.save(entity);
    }
}
