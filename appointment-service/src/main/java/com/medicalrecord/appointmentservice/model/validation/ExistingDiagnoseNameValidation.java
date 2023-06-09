package com.medicalrecord.appointmentservice.model.validation;

import com.medicalrecord.appointmentservice.model.validation.validator.ExistingDiagnoseNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Valid if exist in DB, Not Valid if not exist or soft deleted in DB.
 * Null values are considered valid!
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = ExistingDiagnoseNameValidator.class)
public @interface ExistingDiagnoseNameValidation {

    String message() default "Diagnose with given {name} does not exist!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
