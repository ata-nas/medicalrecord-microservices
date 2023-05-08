package com.medicalrecord.statsservice.exception.notfound;


import com.medicalrecord.statsservice.exception.NoSuchEntityFoundException;

public class NoSuchDoctorEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchDoctorEntityFoundException(String message) {
        super(message);
    }

    public NoSuchDoctorEntityFoundException(String field, String value) {
        super("Doctor", field, value);
    }

}
