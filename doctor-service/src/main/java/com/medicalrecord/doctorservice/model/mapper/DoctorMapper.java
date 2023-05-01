package com.medicalrecord.doctorservice.model.mapper;

import com.medicalrecord.doctorservice.model.dto.doctor.CreateDoctorDTO;
import com.medicalrecord.doctorservice.model.dto.doctor.DoctorDTO;
import com.medicalrecord.doctorservice.model.dto.doctor.UpdateDoctorDTO;
import com.medicalrecord.doctorservice.model.entity.DoctorEntity;
import com.medicalrecord.doctorservice.model.entity.GpEntity;
import com.medicalrecord.doctorservice.model.mapper.util.MapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;

@Mapper(componentModel = "spring", uses = MapperUtil.class)
public interface DoctorMapper {

    @Mapping(target = "isGp", source = "doctorEntity.id", qualifiedByName = "findGpByIdCheckTrue")
    DoctorDTO toDTO(DoctorEntity doctorEntity);

    UpdateDoctorDTO toDTO(CreateDoctorDTO createDoctorDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(source = "specialtiesNames", target = "specialties", qualifiedByName = "findAllSpecialtiesByNameCreateUpdate")
    DoctorEntity toEntity(CreateDoctorDTO createDoctorDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uic", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(source = "specialtiesNames", target = "specialties", qualifiedByName = "findAllSpecialtiesByNameCreateUpdate")
    DoctorEntity toEntity(UpdateDoctorDTO updateDoctorDTO, @MappingTarget DoctorEntity doctorEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(source = "specialtiesNames", target = "specialties", qualifiedByName = "findAllSpecialtiesByNameCreateUpdate")
    GpEntity toEntityGp(CreateDoctorDTO createDoctorDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uic", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(source = "specialtiesNames", target = "specialties", qualifiedByName = "findAllSpecialtiesByNameCreateUpdate")
    GpEntity toEntityGp(UpdateDoctorDTO updateDoctorDTO, @MappingTarget GpEntity gpEntity);

    Set<DoctorDTO> allToDTO(Set<DoctorEntity> doctorEntities);

}
