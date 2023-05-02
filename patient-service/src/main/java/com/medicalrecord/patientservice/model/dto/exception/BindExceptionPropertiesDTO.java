package com.medicalrecord.patientservice.model.dto.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BindExceptionPropertiesDTO {

    private String field;

    private String defaultMessage;

}
