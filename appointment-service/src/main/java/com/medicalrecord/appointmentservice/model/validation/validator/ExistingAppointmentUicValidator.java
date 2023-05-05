package com.medicalrecord.appointmentservice.model.validation.validator;

import com.medicalrecord.appointmentservice.model.validation.ExistingAppointmentUicValidation;
import com.medicalrecord.appointmentservice.repository.AppointmentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingAppointmentUicValidator implements ConstraintValidator<ExistingAppointmentUicValidation, String> {

    private final AppointmentRepository appointmentRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || appointmentRepository.existsByUic(value.toUpperCase());
    }

}
