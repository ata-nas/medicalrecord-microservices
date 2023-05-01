package com.medicalrecord.doctorservice.model.validation.validator;

import com.medicalrecord.doctorservice.model.validation.ExistingDoctorUicValidation;
import com.medicalrecord.doctorservice.repository.DoctorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingDoctorUicValidator implements ConstraintValidator<ExistingDoctorUicValidation, String> {

    private final DoctorRepository doctorRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || doctorRepository.existsByUicAndDeletedFalse(value);
    }

}
