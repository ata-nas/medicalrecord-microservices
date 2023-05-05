package com.medicalrecord.appointmentservice.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface DoctorClient {

    @GetExchange("/api/healthcare/bulgaria/doctors/{uic}")
    public ResponseEntity<Void> doctor(@PathVariable String uic);

}
