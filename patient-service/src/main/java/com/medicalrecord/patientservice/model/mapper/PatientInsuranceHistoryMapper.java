package com.medicalrecord.patientservice.model.mapper;

import com.medicalrecord.patientservice.model.dto.patient.insurance.PatientInsuranceHistoryDTO;
import com.medicalrecord.patientservice.model.entity.PatientInsuranceHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientInsuranceHistoryMapper {

    @Mapping(target = "id", ignore = true)
    PatientInsuranceHistoryEntity toEntity(PatientInsuranceHistoryDTO patientInsuranceHistoryDTO);

}
