package com.restore.core.dto.app;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationHour {

    private Long id;

    @NotBlank(message = "Day of the week is mandatory")
    private DayOfWeek dayOfWeek;

    @NotBlank(message = "Opening time is mandatory")
    private String openingTime;

    @NotBlank(message = "Closing time is mandatory")
    private String closingTime;
}
