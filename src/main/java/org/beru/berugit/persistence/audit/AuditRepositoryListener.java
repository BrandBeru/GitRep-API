/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.persistence.audit;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.beru.berugit.persistence.entity.RepositoryEntity;

/**
 *
 * @author brand
 */
public class AuditRepositoryListener {
    private RepositoryEntity currentValue;
    @PostLoad
    public void postLoad(RepositoryEntity repositoryEntity){
        System.out.println("Post Load");
        this.currentValue = repositoryEntity;
    }
    @PostPersist
    @PostUpdate
    public void onPostPersist(RepositoryEntity enity){
        System.out.println("Post Persist update");
        System.out.println("old value: " + currentValue);
        System.out.println("new value: " + enity.toString());
    }
    @PreRemove
    public void onPreDelete(RepositoryEntity entity){
        System.out.println("Pre Delete");
    }
    
}
