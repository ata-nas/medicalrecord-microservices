package com.medicalrecord.appointmentservice.repository;

import com.medicalrecord.appointmentservice.model.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    Optional<AppointmentEntity> findByUic(String uic);

    boolean existsByUic(String uic);

    @Query(
            "SELECT SUM(p.appointmentFees) FROM AppointmentEntity a JOIN a.price p"
    )
    BigDecimal getTotalIncome();

    @Query(
            "SELECT SUM(p.appointmentFees) FROM AppointmentEntity a JOIN a.price p " +
                    "WHERE a.doctorUic = :uic"
    )
    BigDecimal getDoctorIncomeByUic(String uic);

    @Query(
            "SELECT COUNT(*) " +
                    "FROM (" +
                    "SELECT a.doctorUic AS doctor_uic " +
                    "FROM AppointmentEntity a " +
                    "JOIN a.price p " +
                    "GROUP BY a.doctorUic " +
                    "HAVING SUM(p.appointmentFees) > :income" +
                    ") AS subquery"
    )
    long countDoctorsWithHigherIncomeThanGiven(Long income);

    long countAllByPatientUic(String uic);

    @Query(
            "SELECT COUNT(d) FROM AppointmentEntity a JOIN a.diagnoses d " +
                    "WHERE d.deleted = FALSE AND d.name = :name"
    )
    long countVisitsByDiagnoseName(String name);

}
