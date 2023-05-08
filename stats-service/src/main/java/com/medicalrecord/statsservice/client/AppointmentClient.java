package com.medicalrecord.statsservice.client;

import com.medicalrecord.statsservice.model.dto.stats.DoctorIncomeDTO;
import com.medicalrecord.statsservice.model.dto.stats.TotalIncomeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface AppointmentClient {

    @GetExchange("/api/healthcare/bulgaria/appointments/")
    ResponseEntity<Void> doctor(@PathVariable String uic);

    @GetExchange("/api/healthcare/bulgaria/appointments/income")
    ResponseEntity<TotalIncomeDTO> getTotalIncome();

    @GetExchange("/api/healthcare/bulgaria/appointments/doctor/income/{uic}")
    ResponseEntity<DoctorIncomeDTO> getDoctorIncomeByUic(@PathVariable String uic);

}
