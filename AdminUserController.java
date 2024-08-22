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

import com.springboot.angular.model.LoginUser;
import com.springboot.angular.model.Message;
import com.springboot.angular.service.AdminService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:4200/")
public class AdminUserController {
	
	@Autowired
	private AdminService service;
	@PostMapping("/create")
	public ResponseEntity<Message> saveUser(@RequestBody LoginUser user)
	{
		ResponseEntity<Message> resp=null;
		try {
		String id=service.saveUser(user);
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
	
		//2.fetch all
		@GetMapping("/all")
		public ResponseEntity<List<LoginUser>> getAllUsers(){
			
			List<LoginUser> list = service.getAllUsers();
			return ResponseEntity.ok(list);
		}
		
		//3.fetch
		@GetMapping("/one/{id}")
		public ResponseEntity<LoginUser> getOneUser(@PathVariable String id){
			LoginUser std = service.getOneUser(id);
			System.out.println("getting data from  test" +std);
			return ResponseEntity.ok(std);
			
		}
		
		//4.delete
		@DeleteMapping("/remove/{id}")
		public ResponseEntity<String> deleteUser(@PathVariable String id){
			service.deleteUser(id);
			String msg="Deleted with id:" +id;
			return ResponseEntity.ok(msg);
			
		}
		
		//5.update
		@PutMapping("/update")
		public ResponseEntity<Message> updateUser(@RequestBody LoginUser user ){
			ResponseEntity<Message> resp=null;
			try {
			boolean exist=service.isExist(user.getUserId());
			if(exist) {
			service.saveUser(user);
			resp=new ResponseEntity<Message>(new
			Message("OK",user.getUserId()+"-Updated"),HttpStatus.OK);
			}else {
			resp=new ResponseEntity<Message>(new
			Message("OK",user.getUserId()+"-Not Exist"),HttpStatus.BAD_REQUEST);
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
