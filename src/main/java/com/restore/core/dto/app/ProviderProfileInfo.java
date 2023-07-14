package com.restore.core.dto.app;

import com.restore.core.dto.app.enums.AgeGroupTypes;
import com.restore.core.dto.app.enums.EnrollTypes;
import com.restore.core.dto.app.enums.Languages;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProviderProfileInfo {

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
