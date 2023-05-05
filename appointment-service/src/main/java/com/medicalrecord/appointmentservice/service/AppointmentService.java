package com.medicalrecord.appointmentservice.service;

import com.medicalrecord.appointmentservice.model.dto.appointment.AppointmentDTO;
import com.medicalrecord.appointmentservice.model.dto.appointment.CreateAppointmentDTO;
import com.medicalrecord.appointmentservice.model.dto.appointment.UpdateAppointmentDTO;
import com.medicalrecord.appointmentservice.model.entity.AppointmentEntity;

import java.util.List;

public interface AppointmentService {

    AppointmentEntity getByUic(String uic);

    AppointmentDTO getByUicToDTO(String uic);

    List<AppointmentEntity> getAll();

    List<AppointmentDTO> getAllToDTO();

    AppointmentDTO create(CreateAppointmentDTO createAppointmentDTO);

    AppointmentDTO update(String uic, UpdateAppointmentDTO updateAppointmentDTO);

    void delete(String uic);

}
