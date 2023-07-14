package com.restore.core.dto.app;

import com.restore.core.dto.app.enums.Gender;
import com.restore.core.dto.app.enums.ProviderType;
import com.restore.core.dto.app.enums.UserType;
import jakarta.validation.constraints.*;
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
public class Provider {
    private UUID uuid;
    @NotNull(message = "Provider Type is mandatory")
    private ProviderType providerType;
    @NotNull(message = "Specialities is mandatory")
    private Set<Speciality> specialities;
    @NotNull(message = "Role is mandatory")
    private UserType userType;
    @NotEmpty
    @Size(min=2, message = "Provider name should have at least 2 characters")
    private String name;
    private Gender gender;
    private boolean active = true;
    @NotNull(message = "NPI Number is mandatory")
    @Digits(integer = 10, fraction = 0,message = "NPI Number should have at least 10 numbers.")
    private Long npiNumber;
    @Size(min=10,max=10, message = "Phone number should have at least 10 numbers.")
    @NotNull(message = "Phone Number is mandatory")
    private String phoneNumber;
    private String faxNumber;
    @Digits(integer = 10, fraction = 0,message = "Group NPI Number should have at least 10 numbers.")
    @NotNull(message = "Group NPI Number is mandatory")
    private Long groupNpiNumber;
    @NotBlank(message = "Email id is mandatory")
    @Email(message = "Invalid email format")
    private String email;
    private CountryState licensedState;
    private String licenceNumber;
    private Set<InsurancePayer> insuranceAccepted;
    private String yearOfExperience;
    private String taxonomyNumber;
    @NotNull(message = "Work Location is mandatory")
    private Set<ProviderLocation> workLocations;
    private ProviderProfileInfo providerProfileInfo;
    private String avatar;
    private String newAvatar;
}
