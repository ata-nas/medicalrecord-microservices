package com.medicalrecord.patientservice.exception.handler;

import com.medicalrecord.patientservice.exception.NoSuchEntityFoundException;
import com.medicalrecord.patientservice.model.dto.exception.BindExceptionDTO;
import com.medicalrecord.patientservice.model.dto.exception.GeneralExceptionDTO;
import com.medicalrecord.patientservice.model.mapper.FieldErrorMapper;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class AppExceptionHandler {

    private final FieldErrorMapper fieldErrorMapper;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    private GeneralExceptionDTO handleExceptionFallback(Exception e) {
        return new GeneralExceptionDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL SERVER ERROR!"
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    private BindExceptionDTO handleExceptionBind(BindException e) {
        return new BindExceptionDTO(
                400,
                HttpStatus.BAD_REQUEST,
                e.getFieldErrors()
                        .stream()
                        .map(fieldErrorMapper::toDTO)
                        .toList()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ValidationException.class)
    private GeneralExceptionDTO handleExceptionNotFound(ValidationException e) {
        return new GeneralExceptionDTO(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                e.getLocalizedMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageConversionException.class)
    private GeneralExceptionDTO handleExceptionHttpMessageConversionException(HttpMessageConversionException e) {
        return new GeneralExceptionDTO(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST,
                e.getLocalizedMessage()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchEntityFoundException.class)
    private GeneralExceptionDTO handleExceptionNotFound(NoSuchEntityFoundException e) {
        return e.getData();
    }

}
