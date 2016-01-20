package com.ningzeta.wbm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.ningzeta.wbm.model.Customer;
import com.ningzeta.wbm.model.WaterBill;

public interface WaterBillRepository extends JpaRepository<WaterBill, Long>{
	
	@Procedure(procedureName="generate_bill")
	void generateBill(Date fromDate, Date toDate);
	
	WaterBill findTop1ByCustomer(Customer customer);

}
