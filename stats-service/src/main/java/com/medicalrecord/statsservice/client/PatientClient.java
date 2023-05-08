package com.medicalrecord.statsservice.client;

import com.medicalrecord.statsservice.model.dto.stats.PatientDTO;
import com.medicalrecord.statsservice.model.dto.stats.PercentageInsuredPatientDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Set;

@HttpExchange
public interface PatientClient {

    @GetExchange("/api/healthcare/bulgaria/patients/{uic}")
    ResponseEntity<Void> patient(@PathVariable String uic);

    @GetExchange("/api/healthcare/bulgaria/patients/insured")
    ResponseEntity<Set<PatientDTO>> getAllPatientsCurrentlyInsured();

    @GetExchange("/api/healthcare/bulgaria/patients/not-insured")
    ResponseEntity<Set<PatientDTO>> getAllPatientsCurrentlyNotInsured();

    @GetExchange("/api/healthcare/bulgaria/patients/percent/insured")
    ResponseEntity<PercentageInsuredPatientDTO> getPercentageCurrentlyInsured();

    @GetExchange("/api/healthcare/bulgaria/patients/percent/not-insured")
    ResponseEntity<PercentageInsuredPatientDTO> getPercentageCurrentlyNotInsured();

}
