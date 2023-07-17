package com.restore.core.dto.app;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
    private UUID uuid;

    @NotBlank(message = "User Email is mandatory")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message ="Invalid Email Address" )
    @Size(min = 5,max = 64,message = "Email length should be between 5-64")
    private String email;

    @NotBlank(message = "User First Name is mandatory")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+", message ="Invalid User First Name" )
    @Size(min = 2,max = 32,message = "User First Name length should be between 2-32")
    private String firstName;

    @NotBlank(message = "User Last Name is mandatory")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+", message ="Invalid User Last Name" )
    @Size(min = 2,max = 32,message = "User Last Name length should be between 2-32")
    private String lastName;

    @Size(min = 10,max = 10,message = "Phone length should be 10")
    @NotNull(message = "Phone Number is mandatory")
//    @Pattern(regexp = "^\\+1-?(\\([0-9]{3}\\)|[0-9]{3}-?)[0-9]{3}-?[0-9]{4}$", message ="Invalid User Phone" )
    private String phoneNumber;

    private String avatar;
    private String newAvatar;
}