/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.service;

import java.util.Optional;
import org.beru.berugit.persistence.entity.OrganizationEntity;
import org.beru.berugit.persistence.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author brand
 */
@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }
    public Optional<OrganizationEntity> getByUsername(){
        String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return this.organizationRepository.findByCreatedBy(user);
    }
    public OrganizationEntity save(OrganizationEntity organizationEntity){
        return organizationRepository.save(organizationEntity);
    }
}
