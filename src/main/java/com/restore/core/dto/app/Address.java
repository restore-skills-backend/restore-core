package com.restore.core.dto.app;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    private Long id;

    @NotBlank(message = "Address1 is mandatory field")
    private String line1;

    private String line2;

    @NotBlank(message = "City is mandatory field")
    private String city;

    @NotBlank(message = "State is mandatory field")
    private String state;

    @NotBlank(message = "Country is mandatory field")
    private String country;

    @NotBlank(message = "Zipcode is a mandatory field")
    @Size(min = 5, max = 10, message = "Zipcode should be between 5 and 10 characters")
    private String zipcode;

}
