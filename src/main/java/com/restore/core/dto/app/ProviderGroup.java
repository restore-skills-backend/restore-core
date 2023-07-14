package com.restore.core.dto.app;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProviderGroup {

    private long id;

    private UUID uuid;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String dbSchema;

    private String iamGroup;

    private String subdomain;

    @NotBlank(message = "Contact Number is mandatory")
    @Pattern(regexp = "\\d{10}", message = "Contact Number should be 10 digits")
    private String contactNumber;

    @NotNull(message = "Group NPI Number is mandatory")
    @Digits(integer = 10, fraction = 0, message = "Group Npi Number should be 10 digits")
    private Long npiNumber;

    @NotBlank(message = "Email id is mandatory")
    @Email(message = "Invalid email format")
    private String email;

    private String website;

    private String fax;

    private String description;

    @Valid
    @NotNull(message = "Physical Address is mandatory")
    private Address physicalAddress;

    @Valid
    @NotNull(message = "Billing Address is mandatory")
    private Address billingAddress;

    private boolean active;

    private boolean archive;

    @Valid
//    @NotEmpty(message = "specialities are mandatory")
    private Set<Speciality> specialities;

    @Valid
//    @NotEmpty(message = "Practice Hours are mandatory")
    private Set<PracticeHour> practiceHours;

    private String avatar;

    private String newAvatar;
}