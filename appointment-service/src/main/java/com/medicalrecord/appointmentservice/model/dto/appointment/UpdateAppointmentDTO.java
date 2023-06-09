package com.medicalrecord.appointmentservice.model.dto.appointment;

import com.medicalrecord.appointmentservice.model.validation.ExistingDiagnoseNameValidation;
import com.medicalrecord.appointmentservice.model.validation.ExistingDoctorUicValidation;
import com.medicalrecord.appointmentservice.model.validation.ExistingPatientUicValidation;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderBy;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class UpdateAppointmentDTO {

    @NotNull
    @PastOrPresent
    private LocalDate date;

    @NotBlank
    @ExistingDoctorUicValidation
    private String doctorUic;

    @NotBlank
    @ExistingPatientUicValidation
    private String patientUic;

    @NotBlank
    private String description;

    @NotEmpty
    @ManyToMany
    @OrderBy
    private Set<@ExistingDiagnoseNameValidation String> diagnoses;

}
