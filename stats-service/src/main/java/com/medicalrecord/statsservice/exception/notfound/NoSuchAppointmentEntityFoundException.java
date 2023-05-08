package com.medicalrecord.statsservice.exception.notfound;


import com.medicalrecord.statsservice.exception.NoSuchEntityFoundException;

public class NoSuchAppointmentEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchAppointmentEntityFoundException(String message) {
        super(message);
    }

    public NoSuchAppointmentEntityFoundException(String field, String value) {
        super("Appointment", field, value);
    }

}
