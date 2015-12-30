package com.ningzeta.wbm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ningzeta.wbm.model.PaymentType;
import com.ningzeta.wbm.util.PaymentTypeCode;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long>{
	
	public PaymentType findByPaymentType(PaymentTypeCode paymentType);

}
