package com.medicalrecord.statsservice.model.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class GeneralExceptionDTO {

    private final LocalDateTime timestamp = LocalDateTime.now();

    private final Integer status;

    private final HttpStatus error;

    private final String message;

}
