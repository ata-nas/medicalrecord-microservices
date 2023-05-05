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

}
