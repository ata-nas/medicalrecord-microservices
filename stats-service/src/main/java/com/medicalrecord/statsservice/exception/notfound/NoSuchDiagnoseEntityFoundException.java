package com.medicalrecord.statsservice.exception.notfound;


import com.medicalrecord.statsservice.exception.NoSuchEntityFoundException;

public class NoSuchDiagnoseEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchDiagnoseEntityFoundException(String message) {
        super(message);
    }

    public NoSuchDiagnoseEntityFoundException(String field, String value) {
        super("Diagnose", field, value);
    }

}
