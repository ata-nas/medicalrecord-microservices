package com.medicalrecord.doctorservice.model.validation.validator;

import com.medicalrecord.doctorservice.model.validation.ExistingGpUicValidation;
import com.medicalrecord.doctorservice.repository.GpRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingGpUicValidator implements ConstraintValidator<ExistingGpUicValidation, String> {

    private final GpRepository gpRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || gpRepository.existsByUicAndDeletedFalse(value);
    }

}
