package com.medicalrecord.doctorservice.service.impl;

import com.medicalrecord.doctorservice.exception.notfound.NoSuchSpecialtyEntityFoundException;
import com.medicalrecord.doctorservice.model.dto.specialty.CreateSpecialtyDTO;
import com.medicalrecord.doctorservice.model.dto.specialty.SpecialtyDTO;
import com.medicalrecord.doctorservice.model.entity.SpecialtyEntity;
import com.medicalrecord.doctorservice.model.mapper.SpecialtyMapper;
import com.medicalrecord.doctorservice.repository.SpecialtyRepository;
import com.medicalrecord.doctorservice.service.SpecialtyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyMapper specialtyMapper;

    @Override
    public SpecialtyEntity getByName(String name) {
        return specialtyRepository.findByNameAndDeletedFalse(name)
                .orElseThrow(() -> new NoSuchSpecialtyEntityFoundException("name", name));
    }

    @Override
    public SpecialtyDTO getByNameToDTO(String name) {
        return specialtyMapper.toDTO(getByName(name));
    }

    @Override
    public Set<SpecialtyEntity> getAll() {
        Set<SpecialtyEntity> all = specialtyRepository.findAllByDeletedFalse();
        if (all.isEmpty()) {
            throw new NoSuchSpecialtyEntityFoundException("No Specialties found!");
        }
        return all;
    }

    @Override
    public Set<SpecialtyDTO> getAllToDTO() {
        return specialtyMapper.allToDTO(getAll());
    }

    @Override
    public SpecialtyDTO create(CreateSpecialtyDTO createSpecialtyDTO) {
        if (specialtyRepository.findByName(createSpecialtyDTO.getName()).isPresent()) {
            specialtyRepository.softCreate(createSpecialtyDTO.getName());
            return specialtyMapper.toDTO(createSpecialtyDTO);
        }
        return specialtyMapper.toDTO(
                specialtyRepository.save(specialtyMapper.toEntity(createSpecialtyDTO))
        );
    }

    @Override
    public void delete(String name) {
        specialtyRepository.softDelete(name);
    }

}
