package com.ningzeta.wbm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ningzeta.wbm.model.Meter;

public interface MeterRepository extends JpaRepository<Meter, Long>{

	
	Meter findByMeterId(String meterId);

}
