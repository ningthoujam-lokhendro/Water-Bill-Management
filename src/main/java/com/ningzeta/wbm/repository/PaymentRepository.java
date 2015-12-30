package com.ningzeta.wbm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ningzeta.wbm.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
