package com.medicalrecord.appointmentservice.repository;

import com.medicalrecord.appointmentservice.model.entity.DiagnoseEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DiagnoseRepository extends JpaRepository<DiagnoseEntity, Long> {

    Optional<DiagnoseEntity> findByName(String name);

    Optional<DiagnoseEntity> findByNameAndDeletedFalse(String name);

    boolean existsByNameAndDeletedFalse(String name);

    Set<DiagnoseEntity> findAllByDeletedFalse();

    Set<DiagnoseEntity> findAllByNameInAndDeletedFalse(Set<String> names);

    @Query("UPDATE DiagnoseEntity e SET e.deleted = true WHERE e.name = :name")
    @Modifying(clearAutomatically = true)
    @Transactional
    void softDelete(String name);

    @Query("UPDATE DiagnoseEntity e SET e.deleted = false WHERE e.name = :name")
    @Modifying(clearAutomatically = true)
    @Transactional
    void softCreate(String name);

    /**
     * Count of visits where diagnose exists, soft-deleted are considered not valid!
     *
     * @param name of diagnose
     * @return count of visits where diagnose exists, soft-deleted are considered not counted!
     */
    @Query(
            "SELECT COUNT(d) FROM AppointmentEntity a JOIN a.diagnoses d " +
                    "WHERE d.deleted = FALSE AND d.name = :name"
    )
    long countVisitsByDiagnoseName(String name);

    /**
     * Get total income generated by appointments where diagnose exists, soft-deleted are excluded!
     *
     * @param name of diagnose
     * @return BigDecimal of the total income generated by appointments where diagnose exists, soft-deleted are excluded!
     */
    @Query(
            "SELECT SUM(p.appointmentFees) FROM AppointmentEntity a JOIN a.diagnoses d JOIN a.price p " +
                    "WHERE d.deleted = FALSE AND d.name = :name"
    )
    BigDecimal getTotalIncomeOfDiagnoseName(String name);

}
