package com.medicalrecord.appointmentservice.exception.notfound;


import com.medicalrecord.appointmentservice.exception.NoSuchEntityFoundException;

public class NoSuchAppointmentEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchAppointmentEntityFoundException(String message) {
        super(message);
    }

    public NoSuchAppointmentEntityFoundException(String field, String value) {
        super("Appointment", field, value);
    }

}
