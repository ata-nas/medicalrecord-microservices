package com.medicalrecord.statsservice.service;


import com.medicalrecord.statsservice.model.dto.stats.*;

import java.util.Set;

public interface StatsService {

    Set<PatientDTO> getAllPatientsCurrentlyInsured();

    Set<PatientDTO> getAllPatientsCurrentlyNotInsured();

    PercentageInsuredPatientDTO getPercentageCurrentlyInsured();

    PercentageInsuredPatientDTO getPercentageCurrentlyNotInsured();

    CountDoctorIncomeHigherThanDTO countDoctorsWithHigherIncomeThanGiven(long income);

    TotalIncomeDTO getTotalIncome();

    DoctorIncomeDTO getDoctorIncomeByUic(String uic);

    PatientVisitDTO getPatientVisitCount(String uic);

    DiagnoseVisitDTO getDiagnoseVisitCount(String name);

    DiagnoseIncomeDTO getDiagnoseIncomeByName(String name);

    PatientIncomeDTO getPatientsIncomeFromInsured();

    PatientIncomeDTO getPatientsIncomeFromNotInsured();

    DoctorIncomeDTO getDoctorIncomeByUicFromInsuredPatients(String uic);

    DoctorIncomeDTO getDoctorIncomeByUicFromNotInsuredPatients(String uic);

}
