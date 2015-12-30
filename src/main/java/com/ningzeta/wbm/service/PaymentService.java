package com.ningzeta.wbm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningzeta.wbm.model.Payment;
import com.ningzeta.wbm.model.PaymentStatusCode;
import com.ningzeta.wbm.model.PaymentType;
import com.ningzeta.wbm.model.WaterBill;
import com.ningzeta.wbm.repository.PaymentRepository;
import com.ningzeta.wbm.repository.PaymentStatusCodeRepository;
import com.ningzeta.wbm.repository.PaymentTypeRepository;
import com.ningzeta.wbm.util.PaymentStatus;
import com.ningzeta.wbm.util.PaymentTypeCode;

@Service
@Transactional
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private WaterBillService waterBillService;
	
	@Autowired
	private PaymentStatusCodeRepository paymentSCRepo;
	
	@Autowired
	private PaymentTypeRepository paymentTypeRepo;
	
	/**
	 * Payment Bill.
	 * This should be the only service provided.
	 * 
	 * @param payment
	 * @param billId
	 * @param code
	 * @param paymentTypeCode
	 */
	public void pay(Payment payment, Long billId, PaymentStatus code, PaymentTypeCode paymentTypeCode) {
		
		WaterBill waterBill = waterBillService.findById(billId);
		PaymentStatusCode payStatusCode = paymentSCRepo.findByPaymentStatus(code);
		PaymentType paymentType = paymentTypeRepo.findByPaymentType(paymentTypeCode);
		
		payment.setPaymentStatusCode(payStatusCode);
		payment.setPaymentType(paymentType);
		payment.setWaterBill(waterBill);
		
		paymentRepo.save(payment);
	}
}
