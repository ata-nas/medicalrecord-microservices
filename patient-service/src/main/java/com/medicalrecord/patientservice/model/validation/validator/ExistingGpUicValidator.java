package com.medicalrecord.patientservice.model.validation.validator;

import com.medicalrecord.patientservice.client.DoctorClient;
import com.medicalrecord.patientservice.model.validation.ExistingGpUicValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingGpUicValidator implements ConstraintValidator<ExistingGpUicValidation, String> {

    private final DoctorClient doctorClient;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return doctorClient.gp(value).getStatusCode().is2xxSuccessful();
    }

}
