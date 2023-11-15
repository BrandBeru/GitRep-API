/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.persistence.repository;

import java.util.List;
import java.util.Optional;
import org.beru.berugit.persistence.entity.RepositoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

/**
 *
 * @author brand
 */
public interface RepositoryRepository extends ListCrudRepository<RepositoryEntity, Integer>{
    List<RepositoryEntity> findAllByCreatedBy(String username);
    Optional<RepositoryEntity> findByNameAndCreatedBy(String name, String createdBy);
}
