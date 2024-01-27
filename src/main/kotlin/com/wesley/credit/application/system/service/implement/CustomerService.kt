package com.wesley.credit.application.system.service.implement

import com.wesley.credit.application.system.entity.Customer
import com.wesley.credit.application.system.exception.BusinessException
import com.wesley.credit.application.system.repository.CustomerRepository
import com.wesley.credit.application.system.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer = this.customerRepository.save(customer)

    override fun findById(id: Long): Customer = this.customerRepository.findById(id).orElseThrow {
        throw BusinessException("Id $id not found")
    }

    override fun delete(id: Long) {
        val customer =  this.findById(id)
        this.customerRepository.delete(customer)
    }
}