package com.springboot.angular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.angular.model.LoginUser;
import com.springboot.angular.repo.RegisterUserRepo;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:4200/")
public class RegisterUserController {

	@Autowired
	private RegisterUserRepo repo;
	
	@PostMapping("/register")
	public ResponseEntity<LoginUser> registerUser(@RequestBody LoginUser user)
	{
		
		System.out.println("Controller Called");
		return ResponseEntity.ok(repo.save(user));
	}
}
