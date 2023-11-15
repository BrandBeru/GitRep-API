/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.beru.berugit.persistence.repository;

import org.beru.berugit.persistence.entity.UserRoleEntity;
import org.springframework.data.repository.ListCrudRepository;

/**
 *
 * @author brand
 */
public interface UserRoleRepository extends ListCrudRepository<UserRoleEntity, String>{
    
}
