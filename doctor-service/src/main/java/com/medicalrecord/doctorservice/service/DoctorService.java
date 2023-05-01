package com.medicalrecord.doctorservice.service;

import com.medicalrecord.doctorservice.model.dto.doctor.CreateDoctorDTO;
import com.medicalrecord.doctorservice.model.dto.doctor.DoctorDTO;
import com.medicalrecord.doctorservice.model.dto.doctor.UpdateDoctorDTO;
import com.medicalrecord.doctorservice.model.entity.DoctorEntity;
import com.medicalrecord.doctorservice.model.entity.GpEntity;

import java.util.Set;

public interface DoctorService {

    DoctorEntity getByUic(String uic);

    GpEntity getByUicGp(String uic);

    DoctorDTO getByUicToDTO(String uic);

    Set<DoctorEntity> getAll();

    Set<DoctorDTO> getAllToDTO();

    DoctorDTO create(CreateDoctorDTO createDoctorDTO);

    DoctorDTO update(String uic, UpdateDoctorDTO updateDoctorDTO);

    void delete(String uic);

}
