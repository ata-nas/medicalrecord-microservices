package com.medicalrecord.patientservice.model.dto.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class GeneralExceptionDTO {

    private final LocalDateTime timestamp = LocalDateTime.now();

    private final Integer status;

    private final HttpStatus error;

    private final String  message;

}
