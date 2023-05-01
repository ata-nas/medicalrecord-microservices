package com.medicalrecord.doctorservice.model.dto.specialty;

import com.medicalrecord.doctorservice.model.validation.NotExistingSpecialtyNameValidation;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSpecialtyDTO {

    @NotBlank
    @NotExistingSpecialtyNameValidation
    private String name;

}
