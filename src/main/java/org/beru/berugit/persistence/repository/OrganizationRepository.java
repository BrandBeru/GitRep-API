/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.beru.berugit.persistence.repository;

import java.util.Optional;
import org.beru.berugit.persistence.entity.OrganizationEntity;
import org.springframework.data.repository.ListCrudRepository;

/**
 *
 * @author brand
 */
public interface OrganizationRepository extends ListCrudRepository<OrganizationEntity, Integer>{
    Optional<OrganizationEntity> findByCreatedBy(String createdBy);
}
