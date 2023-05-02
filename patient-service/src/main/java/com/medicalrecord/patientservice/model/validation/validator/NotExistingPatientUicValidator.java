package com.medicalrecord.patientservice.model.validation.validator;

import com.medicalrecord.patientservice.model.validation.NotExistingPatientUicValidation;
import com.medicalrecord.patientservice.repository.PatientRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotExistingPatientUicValidator implements ConstraintValidator<NotExistingPatientUicValidation, String> {

    private final PatientRepository patientRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !patientRepository.existsByUicAndDeletedFalse(value);
    }

}
