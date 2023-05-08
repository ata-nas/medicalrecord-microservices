package com.medicalrecord.appointmentservice.model.mapper;

import com.medicalrecord.appointmentservice.model.dto.appointment.AppointmentDTO;
import com.medicalrecord.appointmentservice.model.dto.appointment.CreateAppointmentDTO;
import com.medicalrecord.appointmentservice.model.dto.appointment.UpdateAppointmentDTO;
import com.medicalrecord.appointmentservice.model.entity.AppointmentEntity;
import com.medicalrecord.appointmentservice.model.mapper.util.MapperUtil;
import com.medicalrecord.appointmentservice.model.stats.DoctorIncomeDTO;
import com.medicalrecord.appointmentservice.model.stats.TotalIncomeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring", uses = MapperUtil.class)
public abstract class AppointmentMapper {

    @Autowired
    private MapperUtil mapperUtil;

    public abstract AppointmentDTO toDTO(AppointmentEntity appointmentEntity);

    public abstract TotalIncomeDTO toDTO(BigDecimal totalIncome);

    public abstract DoctorIncomeDTO toDTODoctorIncome(BigDecimal income);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "price", source = "date", qualifiedByName = "findPricingHistoryInDate")
    @Mapping(target = "diagnoses", source = "diagnoses", qualifiedByName = "findAllDiagnosesByNameCreateUpdate")
    @Mapping(target = "insuredPatient", expression = "java( mapperUtil.insuredPatient(createAppointmentDTO.getPatientUic(), createAppointmentDTO.getDate().toString()) )")
    public abstract AppointmentEntity toEntity(CreateAppointmentDTO createAppointmentDTO);

    @Mapping(target = "uic", ignore = true)
    @Mapping(target = "price", source = "date", qualifiedByName = "findPricingHistoryInDate")
    @Mapping(target = "diagnoses", source = "diagnoses", qualifiedByName = "findAllDiagnosesByNameCreateUpdate")
    @Mapping(target = "insuredPatient", expression = "java( mapperUtil.insuredPatient(updateAppointmentDTO.getPatientUic(), updateAppointmentDTO.getDate().toString()) )")
    public abstract AppointmentEntity toEntity(UpdateAppointmentDTO updateAppointmentDTO, @MappingTarget AppointmentEntity appointmentEntity);

    public abstract List<AppointmentDTO> allToDTO(List<AppointmentEntity> appointments);

    protected boolean insuredPatient(String uic, String date) {
        return mapperUtil.insuredPatient(uic, date);
    }

}
