package com.wesley.credit.application.system.dto

import com.wesley.credit.application.system.entity.Address
import com.wesley.credit.application.system.entity.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto(
    @field: NotEmpty(message = "Oops! It looks like there was an issue with first name.")
    val firstName: String,
    @field: NotEmpty(message = "Oops! It looks like there was an issue with last name.")
    val lastName: String,
    @field: CPF(message = "Oops! It looks like there was an issue with CPF.")
    val cpf: String,
    @field: NotNull(message = "Oops! It looks like there was an issue with income.")
    val income: BigDecimal,
    @field: Email(message = "Oops! It looks like there was an issue with email.")
    val email: String,
    @field: NotEmpty(message = "Oops! It looks like there was an issue with password.")
    val password: String,
    @field: NotEmpty(message = "Oops! It looks like there was an issue with zip code.")
    val zipCode: String,
    @field: NotEmpty(message = "Oops! It looks like there was an issue with street.")
    val street: String
) {
    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        )
    )
}
