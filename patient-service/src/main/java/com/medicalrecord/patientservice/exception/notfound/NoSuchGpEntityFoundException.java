package com.medicalrecord.patientservice.exception.notfound;


import com.medicalrecord.patientservice.exception.NoSuchEntityFoundException;

public class NoSuchGpEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchGpEntityFoundException(String field, String value) {
        super("Gp", field, value);
    }

}
