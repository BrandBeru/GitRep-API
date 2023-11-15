/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author brand
 */
@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Column(name = "organization_id", nullable = true)
    private Integer organizationId;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(name = "last_name", nullable = true, length = 50)
    private String lastName;
    @Id
    @Column(nullable = false, length = 100, unique = true)
    private String username;
    @Column(nullable = false, length = 150, unique = true)
    private String email;
    @Column(nullable = false, length = 200)
    private String password;
    
    @Column(nullable = false, length = 100)
    private String country;
    
    @Column(nullable = true, length = 250)
    private String pictureUrl;
    
    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean disabled;
    
    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean locked;
    
    @OneToMany(mappedBy = "following", fetch = FetchType.EAGER)
    private List<FollowingEntity> following;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<RepositoryEntity> repositories;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles;
    
    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    private OrganizationEntity organization;
    
}
