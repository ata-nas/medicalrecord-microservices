package com.medicalrecord.patientservice.model.validation;

import com.medicalrecord.patientservice.model.validation.validator.NotExistingPatientUicValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Valid if not exist or soft deleted in DB, Not Valid if exist in DB.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotExistingPatientUicValidator.class)
public @interface NotExistingPatientUicValidation {

    String message() default "Patient already exists!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
