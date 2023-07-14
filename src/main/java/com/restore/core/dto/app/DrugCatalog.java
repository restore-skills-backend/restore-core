package com.restore.core.dto.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrugCatalog {
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
