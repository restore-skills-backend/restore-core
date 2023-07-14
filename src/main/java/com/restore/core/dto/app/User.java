package com.restore.core.dto.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.restore.core.dto.app.enums.Roles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    private Long id;

    private UUID uuid;

    @NotBlank(message = "User First Name is mandatory")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+", message ="Invalid User First Name" )
    @Size(min = 2,max = 32,message = "User First Name length should be between 2-32")
    private String firstName;

    @NotBlank(message = "User Last Name is mandatory")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+", message ="Invalid User Last Name" )
    @Size(min = 2,max = 32,message = "User Last Name length should be between 2-32")
    private String lastName;

    @NotBlank(message = "User Email is mandatory")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message ="Invalid Email Address" )
    @Size(min = 5,max = 64,message = "Email length should be between 5-64")
    private String email;

    @Size(min = 12,max = 15,message = "Phone length should be between 12-15")
    @Pattern(regexp = "^\\+1-?(\\([0-9]{3}\\)|[0-9]{3}-?)[0-9]{3}-?[0-9]{4}$", message ="Invalid User Phone" )
    private String phone;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private boolean active;

    private boolean emailVerified;

    private boolean phoneVerified;

    @Builder.Default
    private boolean archive = false;

//    @JsonIgnore
    private String iamId;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String avatar;

    private String newAvatar;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreatedDate
    private Instant created;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @LastModifiedDate
    private Instant modified;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreatedBy
    private String createdBy;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @LastModifiedBy
    private String modifiedBy;

    private String tenantKey;

    private Long lastLogin;
}
