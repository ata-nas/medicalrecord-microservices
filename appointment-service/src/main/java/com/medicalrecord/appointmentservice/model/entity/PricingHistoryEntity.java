package com.medicalrecord.appointmentservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "pricing_history")
public class PricingHistoryEntity extends BaseEntity {

    @NotBlank
    @NaturalId
    @Column(
            name = "issue_no",
            nullable = false
    )
    private String issueNo;

    @NotNull
    @PositiveOrZero
    @Column(
            name = "appointment_fees",
            nullable = false
    )
    private BigDecimal appointmentFees;

    @NotNull
    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

}
