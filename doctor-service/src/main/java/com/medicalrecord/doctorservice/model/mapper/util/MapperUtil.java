package com.medicalrecord.doctorservice.model.mapper.util;

import com.medicalrecord.doctorservice.exception.notfound.NoSuchSpecialtyEntityFoundException;
import com.medicalrecord.doctorservice.model.entity.SpecialtyEntity;
import com.medicalrecord.doctorservice.repository.GpRepository;
import com.medicalrecord.doctorservice.repository.SpecialtyRepository;
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

    private final GpRepository gpRepository;
    private final SpecialtyRepository specialtyRepository;

    @Named("toUpper")
    public static String toUpper(String s) {
        return s.toUpperCase();
    }

    @Named("findGpByIdCheckTrue")
    public boolean findGpByIdCheckTrue(Long id) {
        return id != null && gpRepository.findById(id).isPresent();
    }

    @Named("findAllSpecialtiesByNameCreateUpdate")
    public Set<SpecialtyEntity> findAllSpecialtiesByNameCreateUpdate(Set<String> names) {
        if (names == null) {
            return null;
        }
        Set<SpecialtyEntity> result = specialtyRepository.findAllByNameInAndDeletedFalse(names);
        if (result.isEmpty()) {
            throw new NoSuchSpecialtyEntityFoundException("No Specialties by given {name} found!");
        }
        return result;
    }

}
