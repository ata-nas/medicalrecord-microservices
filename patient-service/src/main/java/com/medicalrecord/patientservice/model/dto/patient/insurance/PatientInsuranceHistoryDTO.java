package com.medicalrecord.patientservice.model.dto.patient.insurance;

import com.medicalrecord.patientservice.model.validation.StartDateBeforeEndDateValidation;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@StartDateBeforeEndDateValidation(first = "startDate", second = "endDate")
public class PatientInsuranceHistoryDTO {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

}
