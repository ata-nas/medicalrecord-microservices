package com.medicalrecord.appointmentservice.model.validation.validator;

import com.medicalrecord.appointmentservice.client.PatientClient;
import com.medicalrecord.appointmentservice.model.validation.ExistingPatientUicValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingPatientUicValidator implements ConstraintValidator<ExistingPatientUicValidation, String> {

    private final PatientClient patientClient;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return patientClient.patient(value).getStatusCode().is2xxSuccessful();
    }

}
