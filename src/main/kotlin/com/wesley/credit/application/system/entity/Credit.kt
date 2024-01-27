package com.wesley.credit.application.system.entity

import com.wesley.credit.application.system.enummeration.Status
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
data class Credit(
    @Id val creditCode : UUID = UUID.randomUUID(),
    @Column(nullable = false) val creditValue : BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false) val dayFirstInstallment : LocalDate,
    @Column(nullable = false) val numberOfInstallments : Int = 0,
    @Enumerated val status : Status = Status.IN_PROGRESS,
    @ManyToOne var customer : Customer? = null,
//    @Column(nullable = false) val id: Long? = null
)