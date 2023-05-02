package com.medicalrecord.patientservice.model.dto.patient;

import com.medicalrecord.patientservice.model.dto.patient.insurance.PatientInsuranceHistoryDTO;
import com.medicalrecord.patientservice.model.validation.ExistingGpUicValidation;
import com.medicalrecord.patientservice.model.validation.NotExistingPatientUicValidation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CreatePatientDTO {

    @NotBlank
    @NotExistingPatientUicValidation
    private String uic;

    @NotBlank
    private String name;

    @ExistingGpUicValidation
    private String gpUic;

    @NotNull
    private Set<@Valid PatientInsuranceHistoryDTO> insurances;

}
