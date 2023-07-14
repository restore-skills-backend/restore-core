package com.restore.core.dto.app;

import com.restore.core.entity.ProviderGroupAddressEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {
    private UUID uuid;
    @NotBlank
    private String name;
    @NotBlank
    private String locationCode;
    @NotEmpty
    private Set<Speciality> specialities;

    private String contactNumber;
    private String avatar;
    @NotBlank
    private String email;
    @NotBlank
    private String fax;
    private String information;
    @NotNull
    private ProviderGroupAddressEntity physicalAddress;
    @NotNull
    private ProviderGroupAddressEntity billingAddress;
    @NotEmpty
    private Set<LocationHour> locationHours;
}
