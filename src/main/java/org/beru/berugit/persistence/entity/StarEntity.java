/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.beru.berugit.persistence.audit.AuditableEntity;
import org.beru.berugit.persistence.entity.id.StarId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author brand
 */
@Entity
@Table(name = "star")
@EntityListeners({AuditingEntityListener.class})
@IdClass(StarId.class)
@Getter
@Setter
@NoArgsConstructor
public class StarEntity extends AuditableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Integer id;
    @Id
    @Column(name = "repository_id", nullable = false)
    private Integer repositoryId;
    
    @ManyToOne
    @JoinColumn(name = "repository_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    //@JoinTable(name = "starred_repository", joinColumns = @JoinColumn(name = "star_id"), inverseJoinColumns = @JoinColumn(name = "repository_id"))
    private RepositoryEntity starredRepository;
    
}
