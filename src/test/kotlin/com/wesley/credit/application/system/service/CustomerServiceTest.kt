package com.wesley.credit.application.system.service

import com.wesley.credit.application.system.entity.Address
import com.wesley.credit.application.system.entity.Customer
import com.wesley.credit.application.system.exception.BusinessException
import com.wesley.credit.application.system.repository.CustomerRepository
import com.wesley.credit.application.system.service.implement.CustomerService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.Optional
import java.util.Random

@ExtendWith(MockKExtension::class)
class CustomerServiceTest {
    @MockK lateinit var customerRepository: CustomerRepository
    @InjectMockKs lateinit var customerService: CustomerService

    @Test
    fun `should create customer`() {
        //given
        val fakeCustomer: Customer = buildCustomer()
        every { customerRepository.save(any()) } returns fakeCustomer
        //when
        val current: Customer = customerService.save(fakeCustomer)
        //then
        Assertions.assertThat(current).isNotNull
        Assertions.assertThat(current).isSameAs(fakeCustomer)
        verify ( exactly = 1 ) { customerRepository.save(fakeCustomer)}
    }

    @Test
    fun `should find customer by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        //when
        val current : Customer = customerService.findById(fakeId)
        //then
        Assertions.assertThat(current).isNotNull
        Assertions.assertThat(current).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(current).isSameAs(fakeCustomer)
        verify ( exactly = 1 ) { customerRepository.findById(fakeId)}
    }

    @Test
    fun `should not find customer by invalid id and throw BusinessException`() {
        //given
        val fakeId: Long = Random().nextLong()
        every { customerRepository.findById(fakeId) } returns Optional.empty()
        //when
        //then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { customerService.findById(fakeId) }
            .withMessage("Id $fakeId not found")
        verify (exactly = 1) { customerRepository.findById(fakeId) }
    }

    @Test
    fun `should delete customer by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        every { customerRepository.delete(fakeCustomer) } just runs
        //when
        customerService.delete(fakeId)
        //then
        verify(exactly = 1) { customerRepository.findById(fakeId) }
        verify(exactly = 1) { customerRepository.delete(fakeCustomer) }
    }

    companion object {
        fun buildCustomer(
            firstName: String = "Well",
            lastName: String = "Johnson",
            cpf: String = "705.436.610-75",
            email: String = "johnson.well@gmail.com",
            password: String = "pass123",
            zipCode: String = "98745",
            street: String = "Johnson Street",
            income: BigDecimal = BigDecimal.valueOf(1000.0),
            id: Long = 1
        ) = Customer(
            firstName = firstName,
            lastName = lastName,
            cpf = cpf,
            email = email,
            password = password,
            address = Address(
                zipCode = zipCode,
                street = street
            ),
            income = income,
            id = id
        )
    }
}