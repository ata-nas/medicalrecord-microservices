package com.medicalrecord.appointmentservice.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface PatientClient {

    @GetExchange("/api/healthcare/bulgaria/patients/{uic}")
    public ResponseEntity<Void> patient(@PathVariable String uic);

    @GetExchange("/api/healthcare/bulgaria/patients/insured/{uic}")
    public ResponseEntity<Void> patientCurrentlyInsured(@PathVariable String uic);

}
