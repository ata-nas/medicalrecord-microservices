package com.medicalrecord.statsservice.client;

import com.medicalrecord.statsservice.model.dto.stats.*;
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

    @GetExchange("/api/healthcare/bulgaria/appointments/doctors/income/above/{income}")
    ResponseEntity<CountDoctorIncomeHigherThanDTO> countDoctorsWithHigherIncomeThanGiven(@PathVariable Long income);

    @GetExchange("/api/healthcare/bulgaria/appointments/patients/visits/{uic}")
    ResponseEntity<PatientVisitDTO> getPatientVisitCount(@PathVariable String uic);

    @GetExchange("/api/healthcare/bulgaria/appointments/diagnoses/visits/{name}")
    ResponseEntity<DiagnoseVisitDTO> getDiagnoseVisitCount(@PathVariable String name);

}
