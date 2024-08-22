package com.springboot.angular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.angular.model.Admin;

import com.springboot.angular.repo.AdminLoginRepo;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="http://localhost:4200")
public class AdminLoginController {

	@Autowired
	private AdminLoginRepo repo;
	@PostMapping("/login")
	public ResponseEntity<?> adminUser(@RequestBody Admin adminData ) {
		
		System.out.println(adminData);
		Admin user=repo.findByUsername(adminData.getUsername());
		if(user.getPassword().equals(adminData.getPassword()))
				
		return ResponseEntity.ok(user);
		return (ResponseEntity<?>) ResponseEntity.internalServerError();
		
	}
}
