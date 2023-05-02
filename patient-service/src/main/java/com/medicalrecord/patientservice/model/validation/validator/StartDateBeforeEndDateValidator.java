package com.medicalrecord.patientservice.model.validation.validator;

import com.medicalrecord.patientservice.model.validation.StartDateBeforeEndDateValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import java.time.LocalDate;

public class StartDateBeforeEndDateValidator implements ConstraintValidator<StartDateBeforeEndDateValidation, Object> {

    private String first;
    private String second;

    @Override
    public void initialize(StartDateBeforeEndDateValidation constraintAnnotation) {
        first = constraintAnnotation.first();
        second = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);

        Object firstValue = beanWrapper.getPropertyValue(first);
        Object secondValue = beanWrapper.getPropertyValue(second);

        if (!(firstValue instanceof LocalDate toCheckFistValue) ||
                !(secondValue instanceof LocalDate toCheckSecondValue)
        ) {
            return false;
        }
        return toCheckFistValue.isBefore(toCheckSecondValue);
    }

}
