package com.medicalrecord.patientservice.repository;

import com.medicalrecord.patientservice.model.entity.PatientEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    Optional<PatientEntity> findByUic(String uic);

    Optional<PatientEntity> findByUicAndDeletedFalse(String uic);

    boolean existsByUicAndDeletedFalse(String uic);

    Set<PatientEntity> findAllByDeletedFalse();

    @Query("UPDATE PatientEntity p SET p.deleted = true WHERE p.uic = :uic")
    @Modifying(clearAutomatically = true)
    @Transactional
    void softDelete(String uic);

    @Query("UPDATE PatientEntity p SET p.deleted = false WHERE p.uic = :uic")
    @Modifying(clearAutomatically = true)
    @Transactional
    void softCreate(String uic);

    /**
     * Patient if is insured currently, otherwise empty optional. Soft-deleted are excluded!
     *
     * @param currDate as of LocalDate.now();
     * @param uic      the uic of the desired patient
     * @return all patients that are currently insured, soft-deleted are excluded!
     */
    @Query(
            "SELECT p FROM PatientEntity p JOIN p.insurances i " +
                    "WHERE (p.uic = :uic) AND (p.deleted = FALSE) AND (:currDate BETWEEN i.startDate AND i.endDate)"
    )
    Optional<PatientEntity> findPatientIfCurrentlyInsuredAndNotDeleted(String uic, LocalDate currDate);

    /**
     * All patients that are currently insured, soft-deleted are excluded!
     *
     * @param currDate as of LocalDate.now();
     * @return all patients that are currently insured, soft-deleted are excluded!
     */
    @Query(
            "SELECT DISTINCT p FROM PatientEntity p JOIN p.insurances i " +
                    "WHERE p.deleted = FALSE AND :currDate BETWEEN i.startDate AND i.endDate"
    )
    Set<PatientEntity> findAllCurrentlyInsured(LocalDate currDate);

    /**
     * All patients that are currently not insured, soft-deleted are excluded!
     *
     * @param currDate as of LocalDate.now();
     * @return all patients that are currently not insured, soft-deleted are excluded!
     */
    @Query(
            "SELECT p FROM PatientEntity p " +
                    "WHERE p.deleted = FALSE AND p NOT IN (SELECT DISTINCT p FROM PatientEntity p JOIN p.insurances i WHERE :currDate BETWEEN i.startDate AND i.endDate)"
    )
    Set<PatientEntity> findAllCurrentlyNotInsured(LocalDate currDate);

    /**
     * Count of all patients that are currently insured, soft-deleted are excluded!
     *
     * @param currDate as of LocalDate.now();
     * @return count of all patients that are currently insured, soft-deleted are excluded!
     */
    @Query(
            "SELECT COUNT(DISTINCT p) FROM PatientEntity p JOIN p.insurances i " +
                    "WHERE (p.deleted = FALSE) AND (:currDate BETWEEN i.startDate AND i.endDate)"
    )
    long countAllCurrentlyInsured(LocalDate currDate);

    /**
     * Count of all patients that are currently not insured, soft-deleted are excluded!
     *
     * @param currDate as of LocalDate.now();
     * @return count of all patients that are currently not insured, soft-deleted are excluded!
     */
    @Query(
            "SELECT COUNT(DISTINCT p) FROM PatientEntity p LEFT JOIN  p.insurances i " +
                    "WHERE (p.deleted = FALSE) AND (p NOT IN (SELECT DISTINCT p FROM PatientEntity p JOIN p.insurances i WHERE :currDate BETWEEN i.startDate AND i.endDate))"
    )
    long countAllCurrentlyNotInsured(LocalDate currDate);

}
