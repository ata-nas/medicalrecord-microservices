package com.medicalrecord.appointmentservice.model.validation.validator;

import com.medicalrecord.appointmentservice.model.validation.NotExistingAppointmentUicValidation;
import com.medicalrecord.appointmentservice.repository.AppointmentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotExistingAppointmentUicValidator implements ConstraintValidator<NotExistingAppointmentUicValidation, String> {

    private final AppointmentRepository appointmentRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !appointmentRepository.existsByUic(value);
    }

}
