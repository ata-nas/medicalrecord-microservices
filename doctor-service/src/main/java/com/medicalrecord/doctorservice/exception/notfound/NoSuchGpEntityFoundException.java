package com.medicalrecord.doctorservice.exception.notfound;

import com.medicalrecord.doctorservice.exception.NoSuchEntityFoundException;

public class NoSuchGpEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchGpEntityFoundException(String field, String value) {
        super("Gp", field, value);
    }

}
