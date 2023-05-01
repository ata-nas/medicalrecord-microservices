package com.medicalrecord.doctorservice.model.mapper;

import com.medicalrecord.doctorservice.model.dto.exception.BindExceptionPropertiesDTO;
import org.mapstruct.Mapper;
import org.springframework.validation.FieldError;

@Mapper(componentModel = "spring")
public interface FieldErrorMapper {

    BindExceptionPropertiesDTO toDTO(FieldError objectError);

}
