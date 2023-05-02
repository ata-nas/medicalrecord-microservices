package com.medicalrecord.patientservice.service;


import com.medicalrecord.patientservice.model.dto.patient.CreatePatientDTO;
import com.medicalrecord.patientservice.model.dto.patient.PatientDTO;
import com.medicalrecord.patientservice.model.dto.patient.PercentageInsuredPatientDTO;
import com.medicalrecord.patientservice.model.dto.patient.UpdatePatientDTO;
import com.medicalrecord.patientservice.model.entity.PatientEntity;

import java.util.Set;

public interface PatientService {

    PatientEntity getByUic(String uic);

    PatientDTO getByUicToDTO(String uic);

    Set<PatientEntity> getAll();

    Set<PatientDTO> getAllToDTO();

    PatientDTO create(CreatePatientDTO createPatientDTO);

    PatientDTO update(String uic, UpdatePatientDTO updatePatientDTO);

    void delete(String uic);

    Set<PatientDTO> getAllPatientsCurrentlyInsured();

    Set<PatientDTO> getAllPatientsCurrentlyNotInsured();

    PercentageInsuredPatientDTO getPercentageCurrentlyInsured();

    PercentageInsuredPatientDTO getPercentageCurrentlyNotInsured();

}
