package com.medicalrecord.patientservice.model.dto.patient;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PercentageInsuredPatientDTO {

    @NotNull
    @PositiveOrZero
    private BigDecimal percentage;

}
