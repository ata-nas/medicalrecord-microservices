package com.medicalrecord.patientservice.web;

import com.medicalrecord.patientservice.model.dto.patient.CreatePatientDTO;
import com.medicalrecord.patientservice.model.dto.patient.PatientDTO;
import com.medicalrecord.patientservice.model.dto.patient.UpdatePatientDTO;
import com.medicalrecord.patientservice.model.validation.ExistingPatientUicValidation;
import com.medicalrecord.patientservice.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.Set;

/**
 * This @RestController handles CRUD for PatientEntity.
 * There is also custom error annotations present here that stop invalid data to enter the @Service,
 * adding additional layer of security and also stopping invalid data to make potentially very taxing operations.
 */
@RestController
@RequestMapping("/api/healthcare/bulgaria/patients")
@RequiredArgsConstructor
@Validated
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/{uic}")
    public ResponseEntity<PatientDTO> patient(
            @PathVariable
            @NotBlank
            @ExistingPatientUicValidation(message = "Illegal path! Patient with given {uic} does not exist!")
            String uic
    ) {
        return ResponseEntity.ok(patientService.getByUicToDTO(uic));
    }

    @GetMapping("/insured/{uic}/{date}")
    public ResponseEntity<Void> patientInsuredAtDate(
            @PathVariable
            @NotBlank
            @ExistingPatientUicValidation(message = "Illegal path! Patient with given {uic} does not exist!")
            String uic,
            @PathVariable
            @NotNull
            LocalDate date
    ) {
        return patientService.patientInsuredByUidAtDate(uic, date) ?
                ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Set<PatientDTO>> patients() {
        return ResponseEntity.ok(patientService.getAllToDTO());
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(
            @RequestBody @Valid CreatePatientDTO createPatientDTO,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .path("/api")
                                .path("/healthcare")
                                .path("/bulgaria")
                                .path("/patients")
                                .path("/" + createPatientDTO.getUic().toLowerCase())
                                .build().toUri()
                )
                .body(patientService.create(createPatientDTO));
    }

    @PutMapping("/{uic}")
    public ResponseEntity<PatientDTO> updatePatient(
            @PathVariable
            @NotBlank
            @ExistingPatientUicValidation(message = "Illegal path! Patient with given {uic} does not exist!")
            String uic,
            @RequestBody @Valid UpdatePatientDTO updatePatientDTO
    ) {
        return ResponseEntity.ok(patientService.update(uic, updatePatientDTO));
    }

    @DeleteMapping("/{uic}")
    public ResponseEntity<PatientDTO> deletePatient(
            @PathVariable
            @NotBlank
            @ExistingPatientUicValidation(message = "Illegal path! Patient with given {uic} does not exist!")
            String uic
    ) {
        patientService.delete(uic);
        return ResponseEntity.noContent().build();
    }

}
