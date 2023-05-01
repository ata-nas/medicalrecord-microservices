package com.medicalrecord.doctorservice.exception.notfound;

import com.medicalrecord.doctorservice.exception.NoSuchEntityFoundException;

public class NoSuchSpecialtyEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchSpecialtyEntityFoundException(String message) {
        super(message);
    }

    public NoSuchSpecialtyEntityFoundException(String field, String value) {
        super("Specialty", field, value);
    }

}
