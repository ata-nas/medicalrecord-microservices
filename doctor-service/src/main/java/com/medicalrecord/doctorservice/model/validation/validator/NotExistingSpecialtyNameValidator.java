package com.medicalrecord.doctorservice.model.validation.validator;

import com.medicalrecord.doctorservice.model.validation.NotExistingSpecialtyNameValidation;
import com.medicalrecord.doctorservice.repository.SpecialtyRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotExistingSpecialtyNameValidator implements ConstraintValidator<NotExistingSpecialtyNameValidation, String> {

    private final SpecialtyRepository specialtyRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !specialtyRepository.existsByNameAndDeletedFalse(value);
    }

}
