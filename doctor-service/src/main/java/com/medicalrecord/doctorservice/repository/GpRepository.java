package com.medicalrecord.doctorservice.repository;

import com.medicalrecord.doctorservice.model.entity.GpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GpRepository extends JpaRepository<GpEntity, Long> {

    Optional<GpEntity> findByUic(String uic);

    Optional<GpEntity> findByUicAndDeletedFalse(String uic);

}
