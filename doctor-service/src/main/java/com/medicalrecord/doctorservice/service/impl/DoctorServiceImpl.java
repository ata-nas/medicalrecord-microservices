package com.medicalrecord.doctorservice.service.impl;

import com.medicalrecord.doctorservice.exception.notfound.NoSuchDoctorEntityFoundException;
import com.medicalrecord.doctorservice.exception.notfound.NoSuchGpEntityFoundException;
import com.medicalrecord.doctorservice.model.dto.doctor.CreateDoctorDTO;
import com.medicalrecord.doctorservice.model.dto.doctor.DoctorDTO;
import com.medicalrecord.doctorservice.model.dto.doctor.UpdateDoctorDTO;
import com.medicalrecord.doctorservice.model.entity.DoctorEntity;
import com.medicalrecord.doctorservice.model.entity.GpEntity;
import com.medicalrecord.doctorservice.model.mapper.DoctorMapper;
import com.medicalrecord.doctorservice.repository.DoctorRepository;
import com.medicalrecord.doctorservice.repository.GpRepository;
import com.medicalrecord.doctorservice.service.DoctorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorMapper doctorMapper;
    private final DoctorRepository doctorRepository;
    /**
     * This GpRepository here because GpEntity inherits DoctorEntity and GpEntity exists only
     * as a flag and DB constraint. So I manage from here.
     */
    private final GpRepository gpRepository;

    @Override
    public DoctorEntity getByUic(String uic) {
        return doctorRepository.findByUicAndDeletedFalse(uic)
                .orElseThrow(() -> new NoSuchDoctorEntityFoundException("uic", uic));
    }

    @Override
    public GpEntity getByUicGp(String uic) {
        return gpRepository.findByUicAndDeletedFalse(uic)
                .orElseThrow(() -> new NoSuchGpEntityFoundException("uic", uic));
    }

    @Override
    public DoctorDTO getByUicToDTO(String uic) {
        return doctorMapper.toDTO(getByUic(uic));
    }

    @Override
    public Set<DoctorEntity> getAll() {
        Set<DoctorEntity> all = doctorRepository.findAllByDeletedFalse();
        if (all.isEmpty()) {
            throw new NoSuchDoctorEntityFoundException("No Doctors found!");
        }
        return all;
    }

    @Override
    public Set<DoctorDTO> getAllToDTO() {
        return doctorMapper.allToDTO(getAll());
    }

    @Override
    public DoctorDTO create(CreateDoctorDTO createDoctorDTO) {
        if (doctorRepository.findByUic(createDoctorDTO.getUic()).isPresent()) {
            doctorRepository.softCreate(createDoctorDTO.getUic());
            return update(createDoctorDTO.getUic(), doctorMapper.toDTO(createDoctorDTO));
        }
        return createDoctorDTO.isGp() ?
                createNewDoctorGp(createDoctorDTO) : createNewDoctor(createDoctorDTO);
    }

    private DoctorDTO createNewDoctor(CreateDoctorDTO createDoctorDTO) {
        return doctorMapper.toDTO(
                doctorRepository.save(doctorMapper.toEntity(createDoctorDTO))
        );
    }

    private DoctorDTO createNewDoctorGp(CreateDoctorDTO createDoctorDTO) {
        return doctorMapper.toDTO(
                doctorRepository.save(doctorMapper.toEntityGp(createDoctorDTO))
        );
    }

    @Override
    public DoctorDTO update(String uic, UpdateDoctorDTO updateDoctorDTO) {
        return doctorMapper.toDTO(
                doctorRepository.save(
                        doctorMapper.toEntity(updateDoctorDTO, getByUic(uic))
                )
        );
    }

    @Override
    public void delete(String uic) {
        doctorRepository.softDelete(uic);
    }

}
