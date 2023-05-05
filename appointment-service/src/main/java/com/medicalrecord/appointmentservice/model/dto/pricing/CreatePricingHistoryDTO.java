package com.medicalrecord.appointmentservice.model.dto.pricing;

import com.medicalrecord.appointmentservice.model.validation.NotConflictingPricingHistoryDateValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CreatePricingHistoryDTO {

    @NotBlank
    private String issueNo;

    @NotNull
    @PositiveOrZero
    private BigDecimal appointmentFees;

    @NotNull
    @NotConflictingPricingHistoryDateValidation
    private LocalDate fromDate;

}
