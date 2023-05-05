package com.medicalrecord.appointmentservice.model.validation.validator;

import com.medicalrecord.appointmentservice.model.validation.NotExistingDiagnoseNameValidation;
import com.medicalrecord.appointmentservice.repository.DiagnoseRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotExistingDiagnoseNameValidator implements ConstraintValidator<NotExistingDiagnoseNameValidation, String> {

    private final DiagnoseRepository diagnoseRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !diagnoseRepository.existsByNameAndDeletedFalse(value);
    }

}
