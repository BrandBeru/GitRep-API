/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.service;

import java.util.List;
import java.util.Optional;
import org.beru.berugit.persistence.entity.RepositoryEntity;
import org.beru.berugit.persistence.repository.RepositoryRepository;
import org.beru.berugit.servers.SSH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author brand
 */
@Service
public class RepositoryService {
    private final RepositoryRepository repository;
    private final SSH ssh;

    @Autowired
    public RepositoryService(RepositoryRepository repository, SSH ssh) {
        this.repository = repository;
        this.ssh = ssh;
    }
    
    public List<RepositoryEntity> getAll(){
        String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return repository.findAllByCreatedBy(user);
    }
    public Optional<RepositoryEntity> getByName(String name){
        String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if(!ssh.connect())
            return null;
        if(!ssh.clone(name, user))
            return null;
        return repository.findByNameAndCreatedBy(name, user);
    }
    public RepositoryEntity save(RepositoryEntity repositoryEntity){
        String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if(!ssh.connect())
            return null;
        if(!ssh.createFolder(repositoryEntity.getName(), user)){
            return null;
        }
        repositoryEntity.setUrl(String.format("%s@%s:%s/%s.git", ssh.getUser(), ssh.getHost(), user, repositoryEntity.getName()));
        return repository.save(repositoryEntity);
    }
    public Optional<RepositoryEntity> remove(int id){
        String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<RepositoryEntity> entity = this.repository.findById(id);
        if(!ssh.connect())
            return null;
        if(!ssh.removeFolder(entity.get().getName(), user))
            return null;
        repository.deleteById(id);
        
        return entity;
    }
}
