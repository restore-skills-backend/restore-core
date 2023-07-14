package com.restore.core.dto.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiagnosisCode {
    private Long id;
    private String diagnosisCode;
    private String description;
//    private String note;
    private boolean active;
}
