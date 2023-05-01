package com.medicalrecord.doctorservice.model.dto.specialty;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecialtyDTO {

    @NotBlank
    private String name;

}
