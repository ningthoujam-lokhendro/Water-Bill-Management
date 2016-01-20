package com.ningzeta.wbm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import com.ningzeta.wbm.model.MeterReading;

public interface MeterReadingRepository extends JpaRepository<MeterReading, Long>{
	
	@Procedure(procedureName="generate_meter_reading")
	void generateMeterReading(Integer p_unit);
}
