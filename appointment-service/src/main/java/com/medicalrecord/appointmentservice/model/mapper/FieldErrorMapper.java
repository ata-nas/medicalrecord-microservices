package com.medicalrecord.appointmentservice.model.mapper;

import com.medicalrecord.appointmentservice.model.dto.exception.BindExceptionPropertiesDTO;
import org.mapstruct.Mapper;
import org.springframework.validation.FieldError;

@Mapper(componentModel = "spring")
public interface FieldErrorMapper {

    BindExceptionPropertiesDTO toDTO(FieldError objectError);

}
