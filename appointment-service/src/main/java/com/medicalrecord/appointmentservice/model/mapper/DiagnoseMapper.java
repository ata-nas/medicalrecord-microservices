package com.medicalrecord.appointmentservice.model.mapper;

import com.medicalrecord.appointmentservice.model.dto.diagnose.CreateDiagnoseDTO;
import com.medicalrecord.appointmentservice.model.dto.diagnose.DiagnoseDTO;
import com.medicalrecord.appointmentservice.model.entity.DiagnoseEntity;
import com.medicalrecord.appointmentservice.model.mapper.util.MapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring", uses = MapperUtil.class)
public interface DiagnoseMapper {

    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    DiagnoseDTO toDTO(DiagnoseEntity diagnoseEntity);

    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    DiagnoseDTO toDTO(CreateDiagnoseDTO createDiagnoseDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    DiagnoseEntity toEntity(CreateDiagnoseDTO createDiagnoseDTO);

    Set<DiagnoseDTO> allToDTO(Set<DiagnoseEntity> diagnoseEntities);

}
