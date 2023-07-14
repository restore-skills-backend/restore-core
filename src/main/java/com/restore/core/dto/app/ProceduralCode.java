package com.restore.core.dto.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProceduralCode {
    private Long id;
//    private String type;
    private String proceduralCode;
    private String description;
//    private String notes;
    private boolean active;
}
