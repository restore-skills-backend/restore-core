package com.restore.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "drug_catalog")
public class DrugCatalogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String speciality;
    private String type;
    private String medicine;
    private String dose;
    private String whenField;
    private String whereField;
    private String frequency;
    private String duration;
    private String quantity;
    private String notes;
    private boolean active;
}
