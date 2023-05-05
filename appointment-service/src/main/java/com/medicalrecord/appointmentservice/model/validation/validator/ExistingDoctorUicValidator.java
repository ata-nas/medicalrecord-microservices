package com.medicalrecord.appointmentservice.model.validation.validator;

import com.medicalrecord.appointmentservice.client.DoctorClient;
import com.medicalrecord.appointmentservice.model.validation.ExistingDoctorUicValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingDoctorUicValidator implements ConstraintValidator<ExistingDoctorUicValidation, String> {

    private final DoctorClient doctorClient;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return doctorClient.doctor(value).getStatusCode().is2xxSuccessful();
    }

}
