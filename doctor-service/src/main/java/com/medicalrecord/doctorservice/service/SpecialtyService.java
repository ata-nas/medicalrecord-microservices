package com.medicalrecord.doctorservice.service;


import com.medicalrecord.doctorservice.model.dto.specialty.CreateSpecialtyDTO;
import com.medicalrecord.doctorservice.model.dto.specialty.SpecialtyDTO;
import com.medicalrecord.doctorservice.model.entity.SpecialtyEntity;

import java.util.Set;

public interface SpecialtyService {

    SpecialtyEntity getByName(String name);

    SpecialtyDTO getByNameToDTO(String name);

    Set<SpecialtyEntity> getAll();

    Set<SpecialtyDTO> getAllToDTO();

    SpecialtyDTO create(CreateSpecialtyDTO createSpecialtyDTO);

    void delete(String name);

}
