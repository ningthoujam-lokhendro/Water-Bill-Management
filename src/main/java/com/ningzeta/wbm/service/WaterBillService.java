package com.ningzeta.wbm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningzeta.wbm.model.WaterBill;
import com.ningzeta.wbm.repository.WaterBillRepository;

@Service
@Transactional
public class WaterBillService {

	@Autowired
	private WaterBillRepository waterBillRepo;
	
	public WaterBill findById(Long id) {
		return waterBillRepo.findOne(id);
	}
}
