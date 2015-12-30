package com.ningzeta.wbm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ningzeta.wbm.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	public Customer findByFirstNameLike(String firstName);
	
}
