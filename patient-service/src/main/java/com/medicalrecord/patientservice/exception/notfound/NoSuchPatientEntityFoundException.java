package com.medicalrecord.patientservice.exception.notfound;


import com.medicalrecord.patientservice.exception.NoSuchEntityFoundException;

public class NoSuchPatientEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchPatientEntityFoundException(String message) {
        super(message);
    }

    public NoSuchPatientEntityFoundException(String field, String value) {
        super("Patient", field, value);
    }

}
