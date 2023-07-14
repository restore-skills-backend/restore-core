package com.restore.core.entity;

import com.restore.core.dto.app.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.UUID;

@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "location")
public class LocationEntity extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private UUID uuid = UUID.randomUUID();

    private String name;
    private String locationId;
    private String contactNumber;
    private String emailId;
    private String faxId;
    private String information;
    private String avatar;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "physical_address_id", referencedColumnName = "id")
    private ProviderGroupAddressEntity physicalAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private ProviderGroupAddressEntity billingAddress;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Set<LocationHoursEntity> locationHoursEntities;

    private boolean active = true;
    private boolean archive;

}
