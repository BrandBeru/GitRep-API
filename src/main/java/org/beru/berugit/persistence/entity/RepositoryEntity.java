/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.beru.berugit.persistence.audit.AuditRepositoryListener;
import org.beru.berugit.persistence.audit.AuditableEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author brand
 */
@Entity
@Table(name = "repository")
@EntityListeners({AuditingEntityListener.class, AuditRepositoryListener.class})
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RepositoryEntity extends AuditableEntity{
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 250, columnDefinition = "TEXT")
    private String description;
    @Column(name = "is_public", nullable = false, columnDefinition = "TINYINT")
    private Boolean isPublic;
    @Column(nullable = false, length = 200)
    private String url;
    
    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "username", insertable = false, updatable = false)
    @JsonIgnore
    private UserEntity user;
    
    @OneToMany(mappedBy = "starredRepository", fetch = FetchType.EAGER)
    private List<StarEntity> starred;
    
}
