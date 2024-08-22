package com.springboot.angular.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.angular.model.RechargePlan;
import com.springboot.angular.service.impl.RechargeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RechargeController {
	@Autowired
	private RechargeService service;
	
	@PostMapping("/add_plan")
	public RechargePlan addPlan(@RequestBody RechargePlan plan) {
		System.out.println("Hello");
		return service.savePlan(plan);
	}
	
	@GetMapping("/get_plan_by_id/{planId}")
	public RechargePlan getPlanById(@PathVariable int planId) throws Exception{
		Optional<RechargePlan> plan = service.fetchRechargePlanByPlanId(planId);
		if(plan==null) {
			throw new Exception("Invalid plan");
		}
		return plan.orElseThrow();
	}
	@GetMapping("/get_plan_by_serviceType/{serviceType}")
	public List<RechargePlan> getPlanByServiceType(@PathVariable String serviceType)throws Exception{
		List<RechargePlan> plans = service.fetchRechargePlanByServiceType(serviceType);
		if(plans==null) {
			throw new Exception("Couldn't Find any");
		}
		return plans;
	}
}
