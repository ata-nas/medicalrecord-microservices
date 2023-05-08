package com.medicalrecord.statsservice.service.impl;

import com.medicalrecord.statsservice.client.AppointmentClient;
import com.medicalrecord.statsservice.client.PatientClient;
import com.medicalrecord.statsservice.exception.notfound.NoSuchPatientEntityFoundException;
import com.medicalrecord.statsservice.model.dto.stats.*;
import com.medicalrecord.statsservice.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.codec.DecodingException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This @Service acts as a binding or orchestration of all other relevant or needed services
 * to have all the querying functionality needed for the task so that the @RestController StatsController
 * can interact only with this class. The actual querying and logic is delegated to the respective @Service.
 */
@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final PatientClient patientClient;
    private final AppointmentClient appointmentClient;

    @Override
    public Set<PatientDTO> getAllPatientsCurrentlyInsured() {
        Set<PatientDTO> body;
        try {
            body = patientClient.getAllPatientsCurrentlyInsured().getBody();
        } catch (DecodingException e) {
            throw new NoSuchPatientEntityFoundException("No Patients currently insured found!");
        }
        return Objects.requireNonNull(body).stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Set<PatientDTO> getAllPatientsCurrentlyNotInsured() {
        Set<PatientDTO> body;
        try {
            body = patientClient.getAllPatientsCurrentlyNotInsured().getBody();
        } catch (DecodingException e) {
            throw new NoSuchPatientEntityFoundException("No Patients currently not insured found!");
        }
        return Objects.requireNonNull(body).stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public PercentageInsuredPatientDTO getPercentageCurrentlyInsured() {
        return patientClient.getPercentageCurrentlyInsured().getBody();
    }

    @Override
    public PercentageInsuredPatientDTO getPercentageCurrentlyNotInsured() {
        return patientClient.getPercentageCurrentlyNotInsured().getBody();
    }

    @Override
    public CountDoctorIncomeHigherThanDTO countDoctorsWithHigherIncomeThanGiven(long income) {
        throw new UnsupportedOperationException("To implement");
    }

    @Override
    public TotalIncomeDTO getTotalIncome() {
        return appointmentClient.getTotalIncome().getBody();
    }

    @Override
    public DoctorIncomeDTO getDoctorIncomeByUic(String uic) {
        return appointmentClient.getDoctorIncomeByUic(uic).getBody();
    }

    @Override
    public PatientVisitDTO getPatientVisitCount(String uic) {
        throw new UnsupportedOperationException("To implement");
//        return appointmentClient.getPatientVisitCount(uic);
    }

    @Override
    public DiagnoseVisitDTO getDiagnoseVisitCount(String name) {
        throw new UnsupportedOperationException("To implement");
//        return appointmentClient.getDiagnoseVisitCount(name);
    }

    @Override
    public DiagnoseIncomeDTO getDiagnoseIncomeByName(String name) {
        throw new UnsupportedOperationException("To implement");
//        return appointmentClient.getDiagnoseIncomeByName(name);
    }

    @Override
    public PatientIncomeDTO getPatientsIncomeFromInsured() {
        throw new UnsupportedOperationException("To implement");
//        return appointmentClient.getPatientsIncomeFromInsured();
    }

    @Override
    public PatientIncomeDTO getPatientsIncomeFromNotInsured() {
        throw new UnsupportedOperationException("To implement");
//        return appointmentClient.getPatientsIncomeFromNotInsured();
    }

    @Override
    public DoctorIncomeDTO getDoctorIncomeByUicFromInsuredPatients(String uic) {
        throw new UnsupportedOperationException("To implement");
//        return appointmentClient.getDoctorIncomeByUicFromInsuredPatients(uic);
    }

    @Override
    public DoctorIncomeDTO getDoctorIncomeByUicFromNotInsuredPatients(String uic) {
        throw new UnsupportedOperationException("To implement");
//        return appointmentClient.getDoctorIncomeByUicFromNotInsuredPatients(uic);
    }

}
