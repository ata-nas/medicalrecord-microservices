package com.medicalrecord.appointmentservice.exception.notfound;


import com.medicalrecord.appointmentservice.exception.NoSuchEntityFoundException;

public class NoSuchDiagnoseEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchDiagnoseEntityFoundException(String message) {
        super(message);
    }

    public NoSuchDiagnoseEntityFoundException(String field, String value) {
        super("Diagnose", field, value);
    }

}
