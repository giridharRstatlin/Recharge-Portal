package com.springboot.angular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.springboot.angular.model.Message;
import com.springboot.angular.model.RechargePlan;
import com.springboot.angular.service.AdminPlanService;

@RestController
@RequestMapping("/plan")
@CrossOrigin(origins="http://localhost:4200/")
public class AdminPlanController {

	@Autowired
	private AdminPlanService service;
	
	@PostMapping("/addplan")
	public ResponseEntity<Message> savePlan(@RequestBody RechargePlan plan)
	{
		ResponseEntity<Message> resp=null;
		try {
		int id=service.savePlan(plan);
		resp=new ResponseEntity<Message>(new
		Message("SUCCESS",id+"-saved"),HttpStatus.OK);
		} catch (Exception e) {
		resp=new ResponseEntity<Message>(new
		Message("FAIL","Unable to Save"),HttpStatus.OK);
		e.printStackTrace();}
		
		//String msg="Student saved with id:"+id ;
		//return ResponseEntity.ok(msg);
		return resp;
	}
	
	@GetMapping("/getplans")
	public ResponseEntity<List<RechargePlan>> getAllPlans(){
		
		List<RechargePlan> list = service.getAllPlans();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/oneplan/{id}")
	public ResponseEntity<RechargePlan> getOnePlan(@PathVariable int id){
		RechargePlan plan = service.getOnePlan(id);
		return ResponseEntity.ok(plan);
		
	}
	
	@DeleteMapping("/removeplan/{id}")
	public ResponseEntity<String> deletePlan(@PathVariable int id){
		service.deletePlan(id);
		String msg="Deleted with id:" +id;
		return ResponseEntity.ok(msg);
		
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Message> updatePlan(@RequestBody RechargePlan plan ){
		ResponseEntity<Message> resp=null;
		try {
		boolean exist=service.isExist(plan.getPlanId());
		if(exist) {
		service.savePlan(plan);
		resp=new ResponseEntity<Message>(new
		Message("OK",plan.getPlanId()+"-Updated"),HttpStatus.OK);
		}else {
		resp=new ResponseEntity<Message>(new
		Message("OK",plan.getPlanId()+"-Not Exist"),HttpStatus.BAD_REQUEST);
		}
		} catch (Exception e) {
		resp=new ResponseEntity<Message>(new
		Message("OK","Unable to Update"),HttpStatus.INTERNAL_SERVER_ERROR);
		e.printStackTrace();
		}
		return resp;
		
		
		
		//service.updateStudent(student);
		//String msg="Student Updated:" +student.getSid();
		//return ResponseEntity.ok(msg);
		
	}
}
