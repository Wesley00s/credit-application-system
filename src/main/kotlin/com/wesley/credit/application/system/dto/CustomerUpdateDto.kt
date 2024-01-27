package com.wesley.credit.application.system.dto

import com.wesley.credit.application.system.entity.Address
import com.wesley.credit.application.system.entity.Customer
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class CustomerUpdateDto(
    @field: NotEmpty(message = "Oops! It looks like there was an issue with first name.")
    val firstName: String,
    @field: NotEmpty(message = "Oops! It looks like there was an issue with last name.")
    val lastName: String,
    @field: NotNull(message = "Oops! It looks like there was an issue with income.")
    val income: BigDecimal,
    @field: NotEmpty(message = "Oops! It looks like there was an issue with zip code.")
    val zipCode: String,
    @field: NotEmpty(message = "Oops! It looks like there was an issue with street.")
    val street: String
) {
    fun toEntity(customer: Customer) : Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address = Address(
            zipCode = this.zipCode,
            street = this.street
        )
        return customer
    }
}
