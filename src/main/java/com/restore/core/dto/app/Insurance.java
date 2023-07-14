package com.restore.core.dto.app;

import com.restore.core.dto.app.enums.RelationshipTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Insurance {
    private String insuranceType;
    private String insurancePayer;
    private String memberId;
    private String planId;
    private String groupId;
    private String groupName;
    private RelationshipTypes policyHolderRelationship;
    private String firstname;
    private String lastname;
    private Instant dob;
    private String country;
    private String address;
    private String zipcode;
}
