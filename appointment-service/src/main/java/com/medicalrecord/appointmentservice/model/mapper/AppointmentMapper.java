package com.medicalrecord.appointmentservice.model.mapper;

import com.medicalrecord.appointmentservice.model.dto.appointment.AppointmentDTO;
import com.medicalrecord.appointmentservice.model.dto.appointment.CreateAppointmentDTO;
import com.medicalrecord.appointmentservice.model.dto.appointment.UpdateAppointmentDTO;
import com.medicalrecord.appointmentservice.model.entity.AppointmentEntity;
import com.medicalrecord.appointmentservice.model.mapper.util.MapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = MapperUtil.class)
public interface AppointmentMapper {

    AppointmentDTO toDTO(AppointmentEntity appointmentEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "price", source = "date", qualifiedByName = "findPricingHistoryInDate")
    @Mapping(target = "diagnoses", source = "diagnoses", qualifiedByName = "findAllDiagnosesByNameCreateUpdate")
    AppointmentEntity toEntity(CreateAppointmentDTO createAppointmentDTO);

    @Mapping(target = "uic", ignore = true)
    @Mapping(target = "price", source = "date", qualifiedByName = "findPricingHistoryInDate")
    @Mapping(target = "diagnoses", source = "diagnoses", qualifiedByName = "findAllDiagnosesByNameCreateUpdate")
    AppointmentEntity toEntity(UpdateAppointmentDTO updateAppointmentDTO, @MappingTarget AppointmentEntity appointmentEntity);

    List<AppointmentDTO> allToDTO(List<AppointmentEntity> appointments);

}
