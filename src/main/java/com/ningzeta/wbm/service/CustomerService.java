package com.ningzeta.wbm.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningzeta.wbm.model.ConnectionType;
import com.ningzeta.wbm.model.Customer;
import com.ningzeta.wbm.model.Meter;
import com.ningzeta.wbm.repository.CustomerRepository;
import com.ningzeta.wbm.util.ConnType;

@Service
@Transactional
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private ConnectionTypeService connTypeService;
	
	@Autowired
	private MetersService meterService;
	
	public List<Customer> getAll() {
		return (List<Customer>) customerRepo.findAll();
	}

	public void createNew(Customer customer, ConnType connType) {
		
		// get the connection type
		ConnectionType conTypeObj = connTypeService.findByType(connType);
		customer.setConnectionType(conTypeObj);
		
		// create Meter Single, Multiple COnnection get param from form and add.
		Meter meter = new Meter();
		meter.setCustomer(customer);
		
		
		Set<Meter> meterMap = new HashSet<Meter>();
		meterMap.add(meter);
		customer.setMeter(meterMap);
		
		customerRepo.save(customer);
		meterService.save(meter);
		
	}
	
	public void update() {
		
		//find the customer and update it.
	}
	
	public Customer findByFirstName(String firstName){
		
		return customerRepo.findByFirstNameLike(firstName);
	}
}
