package com.ningzeta.wbm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningzeta.wbm.model.Meter;
import com.ningzeta.wbm.repository.MeterRepository;

@Service
@Transactional
public class MetersService {

	@Autowired
	MeterRepository meterRepository;
	
	public void save(Meter meter){
		meterRepository.save(meter);
	}

	public Meter findByMeterId(String meterId) {
		return this.meterRepository.findByMeterId(meterId);
	}
}
