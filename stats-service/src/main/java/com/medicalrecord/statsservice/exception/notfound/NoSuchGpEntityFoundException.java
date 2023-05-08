package com.medicalrecord.statsservice.exception.notfound;


import com.medicalrecord.statsservice.exception.NoSuchEntityFoundException;

public class NoSuchGpEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchGpEntityFoundException(String field, String value) {
        super("Gp", field, value);
    }

}
