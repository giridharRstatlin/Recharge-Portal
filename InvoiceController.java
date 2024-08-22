package com.springboot.angular.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.angular.model.Invoice;
import com.springboot.angular.model.RechargePlan;
import com.springboot.angular.service.impl.InvoiceGenerationService;
import com.springboot.angular.service.impl.RechargeService;
import com.springboot.angular.service.impl.RegistrationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class InvoiceController {
	
	@Autowired
	private InvoiceGenerationService invoiceService;
	
	@Autowired
	private RechargeService rechargeService;
	
	@Autowired
	private RegistrationService userService;
	
	@PostMapping(path="/recharge", consumes= {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public Invoice rechargeAccount(@RequestParam("planId") int planId, @RequestParam("userId") String userId) throws Exception {
		Invoice invoice = new Invoice();
		Optional<RechargePlan> plan;
		plan = rechargeService.fetchRechargePlanByPlanId(planId);
		userService.setCurrentPlan(userId, planId);
		invoice.setPlanId(planId);
		invoice.setUserId(userId);
		LocalDate currentDate = java.time.LocalDate.now();
		String dateInput = currentDate.toString();
		invoice.setRechargeDate(dateInput);
		invoice.setRechargeAmount(plan.orElseThrow().getPlanCost());
		Invoice invoiceObj = new Invoice();
		invoiceObj = null;
		invoiceObj = invoiceService.saveInvoice(invoice);
		if(invoiceObj==null) {
			throw new Exception("Recharge could not be completed. Please try again!");
		}
		return invoiceObj;
	}
	
	@GetMapping("/my_invoices/{UserId}")
	public List<Invoice> getUserInvoices(@PathVariable String UserId) throws Exception{
		List<Invoice> resp = invoiceService.fetchInvoiceByUserId(UserId);
		if(resp==null) {
			throw new Exception("No invoices");
		}
		return resp;
	}
	@GetMapping("/getAllInvoices")
	public List<Invoice> getAllInvoices(){
		return invoiceService.fetchAllIssues();
	}
}