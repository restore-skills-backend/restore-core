package com.restore.core.dto.app;

import com.restore.core.entity.LocationHoursEntity;
import com.restore.core.entity.ProviderGroupAddressEntity;
import com.restore.core.entity.SpecialityEntity;
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
public class ProviderLocation {
    private Long id;
    private UUID uuid;
    private String name;
    private String locationId;
    private Set<SpecialityEntity> specialities;
    private String contactNumber;
    private String emailId;
    private String faxId;
    private String information;
    private String avatar;
    private ProviderGroupAddressEntity physicalAddress;
    private ProviderGroupAddressEntity billingAddress;
    private Set<LocationHoursEntity> locationHoursEntities;
}
