package com.medicalrecord.appointmentservice.model.validation.validator;

import com.medicalrecord.appointmentservice.model.validation.ExistingPricingHistoryIssueNoValidation;
import com.medicalrecord.appointmentservice.repository.PricingHistoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingPricingHistoryIssueNoValidator implements ConstraintValidator<ExistingPricingHistoryIssueNoValidation, String> {

    private final PricingHistoryRepository pricingHistoryRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || pricingHistoryRepository.existsByIssueNo(value);
    }

}
