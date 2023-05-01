package com.medicalrecord.doctorservice.model.validation.validator;

import com.medicalrecord.doctorservice.model.validation.NotExistingDoctorUicValidation;
import com.medicalrecord.doctorservice.repository.DoctorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotExistingDoctorUicValidator implements ConstraintValidator<NotExistingDoctorUicValidation, String> {

    private final DoctorRepository doctorRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !doctorRepository.existsByUicAndDeletedFalse(value);
    }

}
