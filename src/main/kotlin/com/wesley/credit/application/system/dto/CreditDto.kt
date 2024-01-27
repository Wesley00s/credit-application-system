package com.wesley.credit.application.system.dto

import com.wesley.credit.application.system.entity.Credit
import com.wesley.credit.application.system.entity.Customer
import jakarta.validation.constraints.*
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field: NotNull(message = "Oops! It looks like there was an issue with credit value.")
    var creditValue: BigDecimal,
    @field: Future(message = "Oops! It looks like there was an issue with day first installments.")
    var dayFirstInstallment: LocalDate,
    @field: Max(value = 48, message = "The maximum number of installments is 48.")
    @field: Min(value = 1, message = "The minimum number of installments is 1.")
    var numberOfInstallments: Int,
    @field: NotNull(message = "Oops! It looks like there was an issue with customer ID.")
    var costumerId: Long
) {

    fun toEntity() : Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.costumerId)
    )
}
