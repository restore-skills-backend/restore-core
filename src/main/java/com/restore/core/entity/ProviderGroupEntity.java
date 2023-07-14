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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "provider_group")
public class ProviderGroupEntity extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private UUID uuid = UUID.randomUUID();

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String dbSchema;

    @Column(unique = true)
    private String iamGroup;

    @Column(unique = true)
    private String subdomain;

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    private Long npiNumber;

    @Column(nullable = false)
    private String email;

    private String website;

    private String fax;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "physical_address_id", referencedColumnName = "id")
    private AddressEntity physicalAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private AddressEntity billingAddress;

    private boolean active;

    private boolean archive;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "provider_group_specialities",
            joinColumns = @JoinColumn(name = "provider_group_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id")
    )
    private Set<SpecialityEntity> specialities;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "provider_group_id", referencedColumnName = "id")
    private Set<PracticeHoursEntity> practiceHours;

    private String avatar;
}
