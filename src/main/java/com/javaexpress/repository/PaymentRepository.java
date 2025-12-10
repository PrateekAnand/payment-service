package com.javaexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
