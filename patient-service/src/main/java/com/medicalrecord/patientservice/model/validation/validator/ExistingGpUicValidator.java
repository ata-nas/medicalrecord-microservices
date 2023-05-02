package com.medicalrecord.patientservice.model.validation.validator;

import com.medicalrecord.patientservice.model.validation.ExistingGpUicValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingGpUicValidator implements ConstraintValidator<ExistingGpUicValidation, String> {

//    private final GpRepository gpRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return true;
//        throw new UnsupportedOperationException("To be implemented");
        // TODO make this to call doctor-service and to see if a gp exists, null are valid, if not exist gp throw exception
//        return value == null || gpRepository.findByUicAndDeletedFalse(value).isPresent();
    }

}
