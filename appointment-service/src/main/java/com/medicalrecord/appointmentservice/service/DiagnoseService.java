package com.medicalrecord.appointmentservice.service;


import com.medicalrecord.appointmentservice.model.dto.diagnose.CreateDiagnoseDTO;
import com.medicalrecord.appointmentservice.model.dto.diagnose.DiagnoseDTO;
import com.medicalrecord.appointmentservice.model.entity.DiagnoseEntity;

import java.util.Set;

public interface DiagnoseService {

    DiagnoseEntity getByName(String name);

    DiagnoseDTO getByNameToDTO(String name);

    Set<DiagnoseEntity> getAll();

    Set<DiagnoseDTO> getAllToDTO();

    DiagnoseDTO create(CreateDiagnoseDTO diagnoseDTO);

    void delete(String name);

}
