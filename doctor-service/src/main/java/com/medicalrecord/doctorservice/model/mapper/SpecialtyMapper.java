package com.medicalrecord.doctorservice.model.mapper;

import com.medicalrecord.doctorservice.model.dto.specialty.CreateSpecialtyDTO;
import com.medicalrecord.doctorservice.model.dto.specialty.SpecialtyDTO;
import com.medicalrecord.doctorservice.model.entity.SpecialtyEntity;
import com.medicalrecord.doctorservice.model.mapper.util.MapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring", uses = MapperUtil.class)
public interface SpecialtyMapper {

    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    SpecialtyDTO toDTO(SpecialtyEntity specialtyEntity);

    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    SpecialtyDTO toDTO(CreateSpecialtyDTO createSpecialtyDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    SpecialtyEntity toEntity(CreateSpecialtyDTO createSpecialtyDTO);

    Set<SpecialtyDTO> allToDTO(Set<SpecialtyEntity> specialtyEntity);

}
