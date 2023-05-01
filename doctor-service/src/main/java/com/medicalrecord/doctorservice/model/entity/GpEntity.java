package com.medicalrecord.doctorservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gps")
public class GpEntity extends DoctorEntity {

    @MapsId
    private Long id;

}
