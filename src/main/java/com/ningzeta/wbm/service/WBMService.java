package com.ningzeta.wbm.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningzeta.wbm.model.ConnectionType;
import com.ningzeta.wbm.model.Customer;
import com.ningzeta.wbm.model.Meter;
import com.ningzeta.wbm.model.MeterReading;
import com.ningzeta.wbm.model.WaterBill;
import com.ningzeta.wbm.repository.CustomerRepository;
import com.ningzeta.wbm.repository.MeterReadingRepository;
import com.ningzeta.wbm.repository.WaterBillRepository;
import com.ningzeta.wbm.util.ConnType;

@Service
@Transactional
public class WBMService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private ConnectionTypeService connTypeService;
	
	@Autowired
	private MetersService meterService;
	
	@Autowired
	private MeterReadingRepository meterReadingService;
	
	@Autowired
	private WaterBillRepository waterBillService;
	
	public void generateMeterReading(int unit) {
		meterReadingService.generateMeterReading(unit);
	}
	
	public void generateBill(Date fromDate, Date toDate) {
		waterBillService.generateBill(fromDate, toDate);
	}
	
	public List<Customer> getAll() {
		return (List<Customer>) customerRepo.findAll();
	}

	public void createNew(Customer customer, ConnType connType, Meter meter) {
		
		// get the connection type
		ConnectionType conTypeObj = connTypeService.findByType(connType);
		customer.setConnectionType(conTypeObj);
		
		// create Meter Single, Multiple COnnection get param from form and add.
		meter.setCustomer(customer);
		
		Set<Meter> meterMap = new HashSet<Meter>();
		meterMap.add(meter);
		customer.setMeter(meterMap);
		
		customerRepo.save(customer);
		meterService.save(meter);
		
	}
	
	public void update(Customer customer) {
		
		customerRepo.save(customer);
	}
	
	public void update() {
		
		//find the customer and update it.
	}
	
	public List<Customer> findByFirstName(String firstName){
		
		return customerRepo.findByFirstNameLike(firstName);
	}
	
	public List<Customer> findByLastName(String lastName){
		
		return customerRepo.findByLastNameLike(lastName);
	}
	
	public List<Customer> findByWardNo(int wardNumber) {
		return customerRepo.findByWardNumber(wardNumber);
	}

	public Customer findById(Long id) {
		// TODO Auto-generated method stub
		return customerRepo.findOne(id);
	}
	
	public void remove(Long id) {
		customerRepo.delete(id);
	}
	
	public Customer findbyPattaNumber(String pattaNumber) {
		return this.customerRepo.findByPattaNumber(pattaNumber);
	}
	
	public Meter findByMeterId(String meterId) {
		return this.meterService.findByMeterId(meterId);
	}
	
	public WaterBill getLast3WaterBill(Customer customer) {
		return waterBillService.findTop1ByCustomer(customer);
	}
}
