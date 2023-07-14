package com.restore.core.dto.app;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LabCatalog {
    private Long id;

    @NotBlank(message = "LOINC NUM cannot be null")
    private String loincNum;

    @NotBlank(message = "Description cannot be null")
    private String description;

    private boolean active;
}
