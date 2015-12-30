package com.ningzeta.wbm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ningzeta.wbm.model.PaymentStatusCode;
import com.ningzeta.wbm.util.PaymentStatus;

public interface PaymentStatusCodeRepository extends JpaRepository<PaymentStatusCode, Long>{

	public PaymentStatusCode findByPaymentStatus(PaymentStatus statusCode);
}
