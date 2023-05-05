package com.medicalrecord.appointmentservice.service;


import com.medicalrecord.appointmentservice.model.dto.pricing.CreatePricingHistoryDTO;
import com.medicalrecord.appointmentservice.model.dto.pricing.PricingHistoryDTO;
import com.medicalrecord.appointmentservice.model.dto.pricing.UpdatePricingHistoryDTO;
import com.medicalrecord.appointmentservice.model.entity.PricingHistoryEntity;

import java.util.List;

public interface PricingHistoryService {

    PricingHistoryEntity getByIssueNo(String issueNo);

    List<PricingHistoryEntity> getAll();

    List<PricingHistoryDTO> getAllToDTO();

    PricingHistoryDTO create(CreatePricingHistoryDTO createPricingHistoryDTO);

    PricingHistoryDTO update(String issueNo, UpdatePricingHistoryDTO updatePricingHistoryDTO);

}
