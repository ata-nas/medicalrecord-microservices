package com.medicalrecord.patientservice.model.validation.validator;

import com.medicalrecord.patientservice.model.validation.ExistingPatientUicValidation;
import com.medicalrecord.patientservice.repository.PatientRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingPatientUicValidator implements ConstraintValidator<ExistingPatientUicValidation, String> {

    private final PatientRepository patientRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || patientRepository.existsByUicAndDeletedFalse(value);
    }

}
