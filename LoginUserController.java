package com.springboot.angular.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.angular.model.LoginUser;
import com.springboot.angular.repo.LoginUserRepo;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:4200")
public class LoginUserController {
 
	@Autowired
	private LoginUserRepo repo;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginUser userData ) {
		
		System.out.println(userData);
		LoginUser user=repo.findByUserId(userData.getUserId());
		System.out.println(user);
		if(user.getPassword().equals(userData.getPassword()))
				
		return ResponseEntity.ok(user);
		return (ResponseEntity<?>) ResponseEntity.internalServerError();
		
	}
	@GetMapping("/suriya")
	public String Mytester() {
		
		return "success";
		
	}
}
