package com.restore.core.dto.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RadiologyCatalog {
    private Long id;
    private String name;
    private String description;
    private boolean active;
}
