package com.medicalrecord.appointmentservice.service.impl;

import com.medicalrecord.appointmentservice.exception.notfound.NoSuchPricingHistoryEntityFoundException;
import com.medicalrecord.appointmentservice.model.dto.pricing.CreatePricingHistoryDTO;
import com.medicalrecord.appointmentservice.model.dto.pricing.PricingHistoryDTO;
import com.medicalrecord.appointmentservice.model.dto.pricing.UpdatePricingHistoryDTO;
import com.medicalrecord.appointmentservice.model.entity.PricingHistoryEntity;
import com.medicalrecord.appointmentservice.model.mapper.PricingHistoryMapper;
import com.medicalrecord.appointmentservice.repository.PricingHistoryRepository;
import com.medicalrecord.appointmentservice.service.PricingHistoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PricingHistoryServiceImpl implements PricingHistoryService {

    private final PricingHistoryRepository pricingHistoryRepository;
    private final PricingHistoryMapper pricingHistoryMapper;

    @Override
    public PricingHistoryEntity getByIssueNo(String issueNo) {
        return pricingHistoryRepository.findByIssueNo(issueNo)
                .orElseThrow(() -> new NoSuchPricingHistoryEntityFoundException("issueNo", issueNo));
    }

    @Override
    public List<PricingHistoryEntity> getAll() {
        List<PricingHistoryEntity> all = pricingHistoryRepository.findAll();
        if (all.isEmpty()) {
            throw new NoSuchPricingHistoryEntityFoundException("No Pricing History found!");
        }
        return all;
    }

    @Override
    public List<PricingHistoryDTO> getAllToDTO() {
        return pricingHistoryMapper.allToDTO(getAll());
    }

    @Override
    public PricingHistoryDTO create(CreatePricingHistoryDTO createPricingHistoryDTO) {
        return pricingHistoryMapper.toDTO(
                pricingHistoryRepository.save(
                        pricingHistoryMapper.toEntity(createPricingHistoryDTO)
                )
        );
    }

    @Override
    public PricingHistoryDTO update(String issueNo, UpdatePricingHistoryDTO updatePricingHistoryDTO) {
        return pricingHistoryMapper.toDTO(
                pricingHistoryRepository.save(
                        pricingHistoryMapper.toEntity(updatePricingHistoryDTO, getByIssueNo(issueNo))
                )
        );
    }

}
