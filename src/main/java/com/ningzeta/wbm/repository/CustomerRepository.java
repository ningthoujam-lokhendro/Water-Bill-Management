package com.ningzeta.wbm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ningzeta.wbm.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	List<Customer> findByFirstNameLike(String firstName);

	List<Customer> findByLastNameLike(String lastName);
		
	List<Customer> findByWardNumber(int wardNumber);
	
	Customer findByPattaNumber(String pattaNumber);
	
}
