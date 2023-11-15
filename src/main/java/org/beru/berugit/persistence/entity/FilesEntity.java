/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.persistence.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author brand
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilesEntity {
    private String name;
    private String type;
    private String content;
    private List<FilesEntity> children;
}
