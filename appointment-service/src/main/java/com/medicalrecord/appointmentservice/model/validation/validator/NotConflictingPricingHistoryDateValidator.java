package com.medicalrecord.appointmentservice.model.validation.validator;

import com.medicalrecord.appointmentservice.model.entity.PricingHistoryEntity;
import com.medicalrecord.appointmentservice.model.validation.NotConflictingPricingHistoryDateValidation;
import com.medicalrecord.appointmentservice.repository.PricingHistoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
public class NotConflictingPricingHistoryDateValidator implements ConstraintValidator<NotConflictingPricingHistoryDateValidation, LocalDate> {

    private final PricingHistoryRepository pricingHistoryRepository;

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        Optional<PricingHistoryEntity> latestDate = pricingHistoryRepository.findLatestPricing();
        return latestDate.isEmpty() || latestDate.get().getFromDate().isBefore(value);
    }

}
