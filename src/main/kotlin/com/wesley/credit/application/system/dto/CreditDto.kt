package com.wesley.credit.application.system.dto

import com.wesley.credit.application.system.entity.Credit
import com.wesley.credit.application.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    var creditValue: BigDecimal,
    var dayFirstInstallment: LocalDate,
    var numberOfInstallments: Int,
    var costumerId: Long
) {

    fun toEntity() : Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.costumerId)
    )
}
