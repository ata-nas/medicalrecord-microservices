package com.medicalrecord.appointmentservice.model.mapper.util;

import com.medicalrecord.appointmentservice.exception.notfound.NoSuchDiagnoseEntityFoundException;
import com.medicalrecord.appointmentservice.exception.notfound.NoSuchPricingHistoryEntityFoundException;
import com.medicalrecord.appointmentservice.model.entity.DiagnoseEntity;
import com.medicalrecord.appointmentservice.model.entity.PricingHistoryEntity;
import com.medicalrecord.appointmentservice.repository.DiagnoseRepository;
import com.medicalrecord.appointmentservice.repository.PricingHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.MapperConfig;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

/**
 * Utility for MapStruct. Here are methods that define what a validly mapped Entity means in my domain.
 * Most of the methods (defined - "...CreateUpdate") that pull from repositories are pulling Entities that are
 * not soft-deleted since that constitutes the current state of the application.
 */
@Component
@MapperConfig
@RequiredArgsConstructor
public class MapperUtil {

    private final DiagnoseRepository diagnoseRepository;
    private final PricingHistoryRepository pricingHistoryRepository;

    @Named("toUpper")
    public static String toUpper(String s) {
        return s.toUpperCase();
    }

    @Named("findPricingHistoryInDate")
    public PricingHistoryEntity findPricingHistoryInDate(LocalDate date) {
        return pricingHistoryRepository.findExistingForDate(date)
                .orElseThrow(() -> new NoSuchPricingHistoryEntityFoundException("date", date.toString()));
    }

    @Named("findAllDiagnosesByNameCreateUpdate")
    public Set<DiagnoseEntity> findDiagnoseByNameCreateUpdate(Set<String> names) {
        Set<DiagnoseEntity> all = diagnoseRepository.findAllByNameInAndDeletedFalse(names);
        if (all.isEmpty()) {
            throw new NoSuchDiagnoseEntityFoundException("No Diagnose by given {name} found!");
        }
        return all;
    }

}
