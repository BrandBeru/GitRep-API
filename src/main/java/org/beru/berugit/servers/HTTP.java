/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.servers;

import org.beru.berugit.persistence.entity.FilesEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author brand
 */
@Component
public class HTTP {
    private RestTemplate restTemplate = new RestTemplate();
    
    @Value("${beru.http.host}")
    private String host;
    
    public FilesEntity getAll(){
        String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        ResponseEntity<FilesEntity> files = restTemplate.getForEntity(host+user, FilesEntity.class);
        
        return files.getBody();
    }
}
