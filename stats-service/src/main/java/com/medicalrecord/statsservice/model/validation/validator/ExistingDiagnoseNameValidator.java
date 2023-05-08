package com.medicalrecord.statsservice.model.validation.validator;

import com.medicalrecord.statsservice.client.DiagnoseClient;
import com.medicalrecord.statsservice.model.validation.ExistingDiagnoseNameValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingDiagnoseNameValidator implements ConstraintValidator<ExistingDiagnoseNameValidation, String> {

    private final DiagnoseClient diagnoseClient;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return diagnoseClient.diagnose(value).getStatusCode().is2xxSuccessful();
    }

}
