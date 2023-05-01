package com.medicalrecord.doctorservice.exception.notfound;

import com.medicalrecord.doctorservice.exception.NoSuchEntityFoundException;

public class NoSuchDoctorEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchDoctorEntityFoundException(String message) {
        super(message);
    }

    public NoSuchDoctorEntityFoundException(String field, String value) {
        super("Doctor", field, value);
    }

}
