package com.medicalrecord.patientservice.model.mapper.util;

import com.medicalrecord.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.MapperConfig;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

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

    private final PatientRepository patientRepository;

    @Named("findGpByUicCreateUpdate")
    public String findGpByUicCreateUpdate(String uic) {
        throw new UnsupportedOperationException("To be implemented");
        // TODO make this method to call doctor-service and check if exists, if not throw exception, if yes return uic, if null return null
    }

}
