package com.medicalrecord.doctorservice.model.validation.validator;

import com.medicalrecord.doctorservice.model.validation.ExistingSpecialtyNameValidation;
import com.medicalrecord.doctorservice.repository.SpecialtyRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingSpecialtyNameValidator implements ConstraintValidator<ExistingSpecialtyNameValidation, String> {

    private final SpecialtyRepository specialtyRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || specialtyRepository.existsByNameAndDeletedFalse(value.toUpperCase());
    }

}
