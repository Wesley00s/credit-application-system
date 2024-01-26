package com.wesley.credit.application.system.repository

import com.wesley.credit.application.system.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
interface CustomerRepository: JpaRepository<Customer, Long>