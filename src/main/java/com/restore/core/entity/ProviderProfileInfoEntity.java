package com.restore.core.entity;

import com.restore.core.dto.app.Base;
import com.restore.core.dto.app.enums.AgeGroupTypes;
import com.restore.core.dto.app.enums.EnrollTypes;
import com.restore.core.dto.app.enums.Languages;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "provider_profile_info")
public class ProviderProfileInfoEntity extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subSpeciality;
    private String hospitalAffilation;
    @Enumerated(EnumType.STRING)
    private AgeGroupTypes ageGroupSeen;
    @Enumerated(EnumType.STRING)
    private Languages languageSpoken;
    private String referralNumber;
    private boolean acceptNewPatients;
    private boolean acceptCashPay;
    @Enumerated(EnumType.STRING)
    private EnrollTypes insuranceVerification;
    @Enumerated(EnumType.STRING)
    private EnrollTypes priorAuthorisation;
    @Enumerated(EnumType.STRING)
    private EnrollTypes secondOpinion;
    @Enumerated(EnumType.STRING)
    private EnrollTypes acuteSpeciality;
    private String bio;
    private String expertiseIn;
    private String workExperience;
}
