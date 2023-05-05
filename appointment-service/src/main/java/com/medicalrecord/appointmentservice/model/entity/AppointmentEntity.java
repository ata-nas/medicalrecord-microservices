package com.medicalrecord.appointmentservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "appointments")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class AppointmentEntity extends BaseEntity {

    @NotBlank
    @NaturalId
    @Column(
            unique = true,
            nullable = false
    )
    @EqualsAndHashCode.Include
    private String uic;

    @NotNull
    @PastOrPresent
    @Column(
            name = "date",
            nullable = false
    )
    private LocalDate date;

    @NotBlank
    @Column(name = "doctor_uic")
    private String doctorUic;

    @NotBlank
    @Column(name = "patient_uic")
    private String patientUic;

    @NotBlank
    @Column(
            nullable = false
    )
    private String description;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @OrderBy
    @JoinTable(
            name = "appointments_diagnoses",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnose_id")
    )
    private Set<@Valid DiagnoseEntity> diagnoses;

    @Valid
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_history_id")
    private PricingHistoryEntity price;

}
