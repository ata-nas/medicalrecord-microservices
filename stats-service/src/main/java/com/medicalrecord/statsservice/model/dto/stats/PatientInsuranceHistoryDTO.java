package com.medicalrecord.statsservice.model.dto.stats;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientInsuranceHistoryDTO {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

}
