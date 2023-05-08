package com.medicalrecord.statsservice.model.dto.stats;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PatientDTO implements Comparable<PatientDTO> {

    @NotBlank
    private String uic;

    @NotBlank
    private String name;

    private String gpUic;

    @NotNull
    private Set<@Valid PatientInsuranceHistoryDTO> insurances;


    @Override
    public int compareTo(PatientDTO other) {
        return uic.compareTo(other.getUic());
    }

}
