package com.ningzeta.wbm.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ningzeta.wbm.model.Customer;
import com.ningzeta.wbm.service.CustomerService;
import com.ningzeta.wbm.util.ConnTypeWrapper;

@Controller
public class MainController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("customer", new Customer());
		model.addAttribute("conntypewrapper", new ConnTypeWrapper());
		return "NewConnection";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute Customer customer,
							   @ModelAttribute ConnTypeWrapper connTypeWrapper,
								Model model) {
		
		model.addAttribute("conntypewrapper", connTypeWrapper);
		customer.setCreationDate(new Date());
		customerService.createNew(customer, connTypeWrapper.getEnumType());
		model.addAttribute("customer", customerService.findByFirstName(customer.getFirstName()));
		return "NewConnectionResult";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search() {
		return "search";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, params="action=all")
	public String getAll(Model model) {
		model.addAttribute("customer", this.customerService.getAll());
		return "SearchResult";
		
	}
	
	@RequestMapping(value="/")
	public String welcome() {
		return "welcome";
	}
	
	private final static Logger log = Logger.getLogger(MainController.class);
	
}
