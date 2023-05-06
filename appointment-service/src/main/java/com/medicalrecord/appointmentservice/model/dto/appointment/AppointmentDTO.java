package com.medicalrecord.appointmentservice.model.dto.appointment;

import com.medicalrecord.appointmentservice.model.dto.diagnose.DiagnoseDTO;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderBy;
import jakarta.validation.Valid;
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
public class AppointmentDTO {

    @NotBlank
    private String uic;

    @NotNull
    @PastOrPresent
    private LocalDate date;

    @NotBlank
    private String doctorUic;

    @NotBlank
    private String patientUic;

    @NotBlank
    private String description;

    @NotEmpty
    @ManyToMany
    @OrderBy
    private Set<@Valid DiagnoseDTO> diagnoses;

    @NotNull
    private Boolean insuredPatient;

}
