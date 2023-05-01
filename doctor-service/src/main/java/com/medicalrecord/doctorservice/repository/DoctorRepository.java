package com.medicalrecord.doctorservice.repository;

import com.medicalrecord.doctorservice.model.entity.DoctorEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    Optional<DoctorEntity> findByUic(String uic);

    Optional<DoctorEntity> findByUicAndDeletedFalse(String uic);

    boolean existsByUicAndDeletedFalse(String uic);

    Set<DoctorEntity> findAllByDeletedFalse();

    @Query("UPDATE DoctorEntity d SET d.deleted = true WHERE d.uic = :uic")
    @Modifying(clearAutomatically = true)
    @Transactional
    void softDelete(String uic);

    @Query("UPDATE DoctorEntity d SET d.deleted = false WHERE d.uic = :uic")
    @Modifying(clearAutomatically = true)
    @Transactional
    void softCreate(String uic);

}
