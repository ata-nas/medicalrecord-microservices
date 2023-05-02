package com.medicalrecord.patientservice.model.mapper;

import com.medicalrecord.patientservice.model.dto.patient.CreatePatientDTO;
import com.medicalrecord.patientservice.model.dto.patient.PatientDTO;
import com.medicalrecord.patientservice.model.dto.patient.UpdatePatientDTO;
import com.medicalrecord.patientservice.model.entity.PatientEntity;
import com.medicalrecord.patientservice.model.mapper.util.MapperUtil;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;

@Mapper(componentModel = "spring", uses = MapperUtil.class, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface PatientMapper {

    PatientDTO toDTO(PatientEntity patientEntity);

    UpdatePatientDTO toDTO(CreatePatientDTO createPatientDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
//    @Mapping(source = "gpUic", target = "gp", qualifiedByName = "findGpByUicCreateUpdate") TODO see if needed
    PatientEntity toEntity(CreatePatientDTO createPatientDTO);

    @Mapping(target = "uic", ignore = true)
    @Mapping(target = "deleted", ignore = true)
//    @Mapping(source = "updatePatientDTO.gpUic", target = "gp", qualifiedByName = "findGpByUicCreateUpdate") TODO see if needed
    PatientEntity toEntity(UpdatePatientDTO updatePatientDTO, @MappingTarget PatientEntity patientEntity);

    Set<PatientDTO> allToDTO(Set<PatientEntity> patientEntity);

}
