package com.restore.core.entity;

import com.restore.core.dto.app.Base;
import com.restore.core.dto.app.enums.Gender;
import com.restore.core.dto.app.enums.ProviderType;
import com.restore.core.dto.app.enums.UserType;
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
@Table(name = "provider")
public class ProviderEntity extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private UserEntity userId;
    @Builder.Default
    private UUID uuid = UUID.randomUUID();
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProviderType providerType;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "provider_specialities",
            joinColumns = @JoinColumn(name = "provider_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<SpecialityEntity> specialities;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(unique = true)
    private Long npiNumber;
    @Column(nullable = false)
    private String phoneNumber;
    private String faxNumber;
    @Column(nullable = false)
    private Long groupNpiNumber;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_state_id", referencedColumnName = "id")
    private CountryStateEntity licensedState;
    private String licenceNumber;
    @ManyToMany(targetEntity = InsurancePayerEntity.class, cascade = {CascadeType.ALL})
    @JoinTable(name = "provider_insurance_payer",
               joinColumns = {@JoinColumn(name = "provider_id")},
               inverseJoinColumns = {@JoinColumn(name = "insurance_payer_id")})
    private Set<InsurancePayerEntity> insuranceAccepted;
    private String yearOfExperience;
    private String taxonomyNumber;
    @ManyToMany(targetEntity = LocationEntity.class, cascade = {CascadeType.ALL})
    @JoinTable(name = "provider_location",
            joinColumns = @JoinColumn(name = "provider_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id"))
    private Set<LocationEntity> workLocations;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "provider_info_id", referencedColumnName = "id")
    private ProviderProfileInfoEntity providerProfileInfo;
    private boolean active = true;
    private boolean archive;
    private String avtar;

}
