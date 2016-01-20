package com.ningzeta.wbm.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ningzeta.wbm.model.Customer;
import com.ningzeta.wbm.model.Meter;
import com.ningzeta.wbm.model.Payment;
import com.ningzeta.wbm.model.WaterBill;
import com.ningzeta.wbm.service.WBMService;
import com.ningzeta.wbm.util.BillStatusCode;
import com.ningzeta.wbm.util.ConnTypeWrapper;


@Controller
public class MainController {
	
	@Autowired
	WBMService service;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("customer", new Customer());
		model.addAttribute("meter", new Meter());
		model.addAttribute("conntypewrapper", new ConnTypeWrapper());
		return "NewConnection";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute Customer customer,
						 @ModelAttribute ConnTypeWrapper connTypeWrapper,
						 @ModelAttribute Meter meter,
						 Model model) {
		
		model.addAttribute("conntypewrapper", connTypeWrapper);
		customer.setCreationDate(new Date());
		service.createNew(customer, connTypeWrapper.getEnumType(), meter);
		model.addAttribute("customer", this.service.findbyPattaNumber(customer.getPattaNumber()));
		Meter m = this.service.findByMeterId(meter.getMeterId());
		model.addAttribute("meter", m);
		return "customerinfo";
	}
	
	@RequestMapping(value = "/update")
	public String update(@RequestParam("id") Long id, Model model) {
		Customer customer = this.service.findById(id);
		model.addAttribute("customer", customer);
		model.addAttribute("meter", customer.getMeter());
		return "update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute Customer customer,
						 @ModelAttribute ConnTypeWrapper connTypeWrapper,
						 @ModelAttribute Meter meter,
						 Model model) {
		
		Customer fetchCus = this.service.findById(customer.getId());
		merge(customer, fetchCus);
		model.addAttribute("conntypewrapper", fetchCus.getConnectionType());
		service.update(fetchCus);
		model.addAttribute("customer", fetchCus);
		model.addAttribute("meter", fetchCus.getMeter());
		return "customerinfo";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Model model) {
		model.addAttribute("customer", new Customer());
		return "search";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, params="action=all")
	public String getAll(Model model) {
		log.info("Request Received for all");
		model.addAttribute("customers", this.service.getAll());
		return "SearchResult";
		
	}
	
	@RequestMapping(value = "/search/customer/{id}")
	public String view(@PathVariable Long id, Model model) {
		Customer customer = this.service.findById(id);
		model.addAttribute("customer", customer);
		model.addAttribute("meter", customer.getMeter());
		WaterBill wb = this.service.getLast3WaterBill(customer);
		model.addAttribute("waterbill", wb);
		model.addAttribute("duestatus", wb.getStatus().getBillStatusCode().equals(BillStatusCode.DUE)? true:false);
		
		return "customerinfo";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@RequestParam("lastname") String lastName,
						 @RequestParam("firstname") String firstName,
						 @RequestParam("wardnumber") int wardNumber,
						 @RequestParam("pattanumber") String pattaNumber,
						 Model model) {
		
		if(pattaNumber.length() > 0)
			model.addAttribute("customers", this.service.findbyPattaNumber(pattaNumber));
		else if(lastName.length() > 0 )
			model.addAttribute("customers", this.service.findByLastName(lastName));
		else if(firstName.length() > 0)
			model.addAttribute("customers", this.service.findByFirstName(firstName));
		else if(wardNumber > 0 && wardNumber < 13)
			model.addAttribute("customers", this.service.findByWardNo(wardNumber));
		else
			model.addAttribute("customer", new Customer());
		
		return "SearchResult";
	}
	
	@RequestMapping(value="/generate/meterreading")
	public String generateReading() {
		return "generatereading";
	}
	
	@RequestMapping(value="/generate/meterreading",method = RequestMethod.POST)
	public String generateMR(@RequestParam("unit") int unit) {
		service.generateMeterReading(unit);
		return "welcome";
	}
	
	@RequestMapping(value="/generate/bill")
	public String generateBill() {
		return "generatebill";
	}
	
	@RequestMapping(value="/generate/bill",method = RequestMethod.POST)
	public String generateBill(@RequestParam("fromdate") Date fromDate,
						   @RequestParam("todate") Date toDate) {
		service.generateBill(fromDate, toDate);
		return "welcome";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam("id") Long id) {
		this.service.remove(id);
		return "welcome";
	}
	
	@RequestMapping(value = "/paybill", method = RequestMethod.POST)
	public String payBill(Model model) {
		model.addAttribute("payment", new Payment());
		return "welcome";
	}
	
	@RequestMapping(value = "/paybill", method = RequestMethod.GET)
	public String payForm(@RequestParam("billnumber") int billNumber,
						  @RequestParam("dueamount") int amount,
						  Model model) {

		model.addAttribute("billnumber", billNumber);
		model.addAttribute("dueamount", amount);
		model.addAttribute("payment", new Payment());
		return "paybill";
	}
	
	/**
	 * @param customer
	 * @param fetchCus
	 */
	private void merge(Customer customer, Customer fetchCus) {
		String address = customer.getAddress();
		String landLine = customer.getLandLineNumber();
		String mobile = customer.getMobileNumber();
		int wardNumber = customer.getWardNumber();
		
		if( !address.isEmpty() || address.length() != 0 )
			fetchCus.setAddress(address);
		if( landLine.isEmpty() || landLine.length() != 0 )
			fetchCus.setLandLineNumber(landLine);
		if (mobile.isEmpty() || mobile.length() != 0 )
			fetchCus.setMobileNumber(mobile);
		if(wardNumber > 0 && wardNumber <14)
			fetchCus.setWardNumber(wardNumber);
	}
	
	@RequestMapping(value="/")
	public String welcome() {
		return "welcome";
	}
	
	private final static Logger log = Logger.getLogger(MainController.class);
	
}
