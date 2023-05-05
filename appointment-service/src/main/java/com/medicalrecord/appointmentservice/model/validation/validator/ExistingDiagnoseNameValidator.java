package com.medicalrecord.appointmentservice.model.validation.validator;

import com.medicalrecord.appointmentservice.model.validation.ExistingDiagnoseNameValidation;
import com.medicalrecord.appointmentservice.repository.DiagnoseRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingDiagnoseNameValidator implements ConstraintValidator<ExistingDiagnoseNameValidation, String> {

    private final DiagnoseRepository diagnoseRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || diagnoseRepository.existsByNameAndDeletedFalse(value.toUpperCase());
    }

}
