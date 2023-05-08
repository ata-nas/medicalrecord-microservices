package com.medicalrecord.appointmentservice.service.impl;

import com.medicalrecord.appointmentservice.exception.notfound.NoSuchAppointmentEntityFoundException;
import com.medicalrecord.appointmentservice.model.dto.appointment.AppointmentDTO;
import com.medicalrecord.appointmentservice.model.dto.appointment.CreateAppointmentDTO;
import com.medicalrecord.appointmentservice.model.dto.appointment.UpdateAppointmentDTO;
import com.medicalrecord.appointmentservice.model.entity.AppointmentEntity;
import com.medicalrecord.appointmentservice.model.mapper.AppointmentMapper;
import com.medicalrecord.appointmentservice.model.stats.DoctorIncomeDTO;
import com.medicalrecord.appointmentservice.model.stats.TotalIncomeDTO;
import com.medicalrecord.appointmentservice.repository.AppointmentRepository;
import com.medicalrecord.appointmentservice.service.AppointmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public AppointmentEntity getByUic(String uic) {
        return appointmentRepository.findByUic(uic)
                .orElseThrow(() -> new NoSuchAppointmentEntityFoundException("uic", uic));
    }

    @Override
    public AppointmentDTO getByUicToDTO(String uic) {
        return appointmentMapper.toDTO(getByUic(uic));
    }

    @Override
    public List<AppointmentEntity> getAll() {
        List<AppointmentEntity> all = appointmentRepository.findAll();
        if (all.isEmpty()) {
            throw new NoSuchAppointmentEntityFoundException("No Appointments found!");
        }
        return all;
    }

    @Override
    public List<AppointmentDTO> getAllToDTO() {
        return appointmentMapper.allToDTO(getAll());
    }

    @Override
    public AppointmentDTO create(CreateAppointmentDTO createAppointmentDTO) {
        return appointmentMapper.toDTO(
                appointmentRepository.save(
                        appointmentMapper.toEntity(createAppointmentDTO)
                )
        );
    }

    @Override
    public AppointmentDTO update(String uic, UpdateAppointmentDTO updateAppointmentDTO) {
        return appointmentMapper.toDTO(
                appointmentRepository.save(
                        appointmentMapper.toEntity(updateAppointmentDTO, getByUic(uic))
                )
        );
    }

    @Override
    public void delete(String uic) {
        appointmentRepository.delete(getByUic(uic));
    }

    @Override
    public TotalIncomeDTO getTotalIncome() {
        return appointmentMapper.toDTO(appointmentRepository.getTotalIncome());
    }

    @Override
    public DoctorIncomeDTO getDoctorIncomeByUic(String uic) {
        return appointmentMapper.toDTODoctorIncome(appointmentRepository.getDoctorIncomeByUic(uic));
    }

}
