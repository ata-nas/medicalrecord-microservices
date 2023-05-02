package com.medicalrecord.patientservice.service.impl;

import com.medicalrecord.patientservice.exception.notfound.NoSuchPatientEntityFoundException;
import com.medicalrecord.patientservice.model.dto.patient.CreatePatientDTO;
import com.medicalrecord.patientservice.model.dto.patient.PatientDTO;
import com.medicalrecord.patientservice.model.dto.patient.PercentageInsuredPatientDTO;
import com.medicalrecord.patientservice.model.dto.patient.UpdatePatientDTO;
import com.medicalrecord.patientservice.model.entity.PatientEntity;
import com.medicalrecord.patientservice.model.mapper.PatientMapper;
import com.medicalrecord.patientservice.repository.PatientRepository;
import com.medicalrecord.patientservice.service.PatientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientEntity getByUic(String uic) {
        return patientRepository.findByUicAndDeletedFalse(uic)
                .orElseThrow(() -> new NoSuchPatientEntityFoundException("uic", uic));
    }

    @Override
    public PatientDTO getByUicToDTO(String uic) {
        return patientMapper.toDTO(getByUic(uic));
    }

    @Override
    public Set<PatientEntity> getAll() {
        Set<PatientEntity> all = patientRepository.findAllByDeletedFalse();
        if (all.isEmpty()) {
            throw new NoSuchPatientEntityFoundException("No Patients found!");
        }
        return all;
    }

    @Override
    public Set<PatientDTO> getAllToDTO() {
        return patientMapper.allToDTO(getAll());
    }

    @Override
    public PatientDTO create(CreatePatientDTO createPatientDTO) {
        if (patientRepository.findByUic(createPatientDTO.getUic()).isPresent()) {
            patientRepository.softCreate(createPatientDTO.getUic());
            return update(createPatientDTO.getUic(), patientMapper.toDTO(createPatientDTO));
        }
        return patientMapper.toDTO(
                patientRepository.save(
                        patientMapper.toEntity(createPatientDTO)
                )
        );
    }

    @Override
    public PatientDTO update(String uic, UpdatePatientDTO updatePatientDTO) {
        return patientMapper.toDTO(
                patientRepository.save(patientMapper.toEntity(updatePatientDTO, getByUic(uic)))
        );
    }

    @Override
    public void delete(String uic) {
        patientRepository.softDelete(uic);
    }

    @Override
    public Set<PatientDTO> getAllPatientsCurrentlyInsured() {
        Set<PatientDTO> all = patientMapper.allToDTO(patientRepository.findAllCurrentlyInsured(LocalDate.now()));
        if (all.isEmpty()) {
            throw new NoSuchPatientEntityFoundException("No Patients currently insured found!");
        }
        return all;
    }

    @Override
    public Set<PatientDTO> getAllPatientsCurrentlyNotInsured() {
        Set<PatientDTO> all = patientMapper.allToDTO(patientRepository.findAllCurrentlyNotInsured(LocalDate.now()));
        if (all.isEmpty()) {
            throw new NoSuchPatientEntityFoundException("No Patients currently not insured found!");
        }
        return all;
    }

    @Override
    public PercentageInsuredPatientDTO getPercentageCurrentlyInsured() {
        return new PercentageInsuredPatientDTO().setPercentage(calculatePercentInsured());
    }

    private BigDecimal calculatePercentInsured() {
        long all = patientRepository.findAllByDeletedFalse().size();
        long insured = patientRepository.countAllCurrentlyInsured(LocalDate.now());
        return BigDecimal.valueOf(insured)
                .multiply(BigDecimal.valueOf(100.00))
                .setScale(4, RoundingMode.HALF_EVEN)
                .divide(BigDecimal.valueOf(all), RoundingMode.HALF_EVEN);
    }

    @Override
    public PercentageInsuredPatientDTO getPercentageCurrentlyNotInsured() {
        return new PercentageInsuredPatientDTO().setPercentage(calculatePercentNotInsured());
    }

    private BigDecimal calculatePercentNotInsured() {
        long all = patientRepository.findAllByDeletedFalse().size();
        long insured = patientRepository.countAllCurrentlyNotInsured(LocalDate.now());
        return BigDecimal.valueOf(insured)
                .multiply(BigDecimal.valueOf(100.00))
                .setScale(4, RoundingMode.HALF_EVEN)
                .divide(BigDecimal.valueOf(all), RoundingMode.HALF_EVEN);
    }

}
