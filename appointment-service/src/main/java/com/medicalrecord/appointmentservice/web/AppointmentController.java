package com.medicalrecord.appointmentservice.web;

import com.medicalrecord.appointmentservice.model.dto.appointment.AppointmentDTO;
import com.medicalrecord.appointmentservice.model.dto.appointment.CreateAppointmentDTO;
import com.medicalrecord.appointmentservice.model.dto.appointment.UpdateAppointmentDTO;
import com.medicalrecord.appointmentservice.model.dto.stats.*;
import com.medicalrecord.appointmentservice.model.validation.ExistingAppointmentUicValidation;
import com.medicalrecord.appointmentservice.model.validation.ExistingDiagnoseNameValidation;
import com.medicalrecord.appointmentservice.model.validation.ExistingDoctorUicValidation;
import com.medicalrecord.appointmentservice.model.validation.ExistingPatientUicValidation;
import com.medicalrecord.appointmentservice.service.AppointmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * This @RestController handles CRUD for AppointmentEntity.
 * There is also custom error annotations present here that stop invalid data to enter the @Service,
 * adding additional layer of security and also stopping invalid data to make potentially very taxing operations.
 */
@RestController
@RequestMapping("/api/healthcare/bulgaria/appointments")
@RequiredArgsConstructor
@Validated
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/{uic}")
    public ResponseEntity<AppointmentDTO> appointment(
            @NotBlank
            @ExistingAppointmentUicValidation
            @PathVariable
            String uic
    ) {
        return ResponseEntity.ok(appointmentService.getByUicToDTO(uic));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> appointments() {
        return ResponseEntity.ok(appointmentService.getAllToDTO());
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> create(
            @RequestBody @Valid CreateAppointmentDTO createAppointmentDTO,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .path("/api")
                                .path("/healthcare")
                                .path("/bulgaria")
                                .path("/appointments")
                                .path("/" + createAppointmentDTO.getUic())
                                .build().toUri()
                )
                .body(appointmentService.create(createAppointmentDTO));
    }

    @PutMapping("/{uic}")
    public ResponseEntity<AppointmentDTO> update(
            @PathVariable
            @NotBlank
            @ExistingAppointmentUicValidation(message = "Invalid path! Appointment with given {uic} does not exist!")
            String uic,
            @RequestBody @Valid UpdateAppointmentDTO updateAppointmentDTO
    ) {
        return ResponseEntity.ok(appointmentService.update(uic, updateAppointmentDTO));
    }

    @DeleteMapping("/{uic}")
    public ResponseEntity<AppointmentDTO> delete(
            @PathVariable
            @NotBlank
            @ExistingAppointmentUicValidation(message = "Invalid path! Appointment with given {uic} does not exist!")
            String uic
    ) {
        appointmentService.delete(uic);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/income")
    public ResponseEntity<TotalIncomeDTO> getTotalIncome() {
        return ResponseEntity.ok(appointmentService.getTotalIncome());
    }

    @GetMapping("/doctor/income/{uic}")
    public ResponseEntity<DoctorIncomeDTO> getDoctorIncomeByUic(
            @PathVariable
            @NotNull
            @ExistingDoctorUicValidation(message = "Invalid path! Doctor with given {uic} does not exist!")
            String uic
    ) {
        return ResponseEntity.ok(appointmentService.getDoctorIncomeByUic(uic));
    }

    @GetMapping("/doctors/income/above/{income}")
    public ResponseEntity<CountDoctorIncomeHigherThanDTO> countDoctorsWithHigherIncomeThanGiven(
            @PathVariable
            @NotNull
            @PositiveOrZero
            Long income
    ) {
        return ResponseEntity.ok(appointmentService.countDoctorsWithHigherIncomeThanGiven(income));
    }

    @GetMapping("/patients/visits/{uic}")
    public ResponseEntity<PatientVisitDTO> getPatientVisitCount(
            @PathVariable
            @NotNull
            @ExistingPatientUicValidation(message = "Invalid path! Patient with given {uic} does not exist!")
            String uic
    ) {
        return ResponseEntity.ok(appointmentService.getPatientVisitCount(uic));
    }

    @GetMapping("/diagnoses/visits/{name}")
    public ResponseEntity<DiagnoseVisitDTO> getDiagnoseVisitCount(
            @PathVariable
            @NotNull
            @ExistingDiagnoseNameValidation(message = "Invalid path! Diagnose with given {name} does not exist!")
            String name
    ) {
        return ResponseEntity.ok(appointmentService.getDiagnoseVisitCount(name));
    }

}
