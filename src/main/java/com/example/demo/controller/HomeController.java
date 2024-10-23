package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Users;
import com.example.demo.dto.ApiResponse;

import com.example.demo.service.userservice;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin
@RequestMapping("/api")
public class HomeController {
	@Autowired
	private userservice service;
	@GetMapping("/")
	public String Greet() {
		return "Hello SpringMVC App";
	}
	
	@GetMapping("/about")
	public String Hello() {
		return "Reload Done";

	}
	@GetMapping("/get_all_users")
	
	public ResponseEntity<ApiResponse<List<Users>>> getUsers(){
		List<Users> User = service.getAllUsers();
		if(User.isEmpty()) {
			ApiResponse<List<Users>> response = new ApiResponse<>(200,Collections.emptyList(),"No Users Found");

			return ResponseEntity.ok(response);
		}
		ApiResponse<List<Users>> response = new ApiResponse<>(200, User,"Success");

		return ResponseEntity.ok(response);

		
	}
	@GetMapping("/get_user_byID/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
	    Optional<Users> user = service.getUserByID(id);

	    // If user is found, return with HTTP 200 (OK) status
	    if (user.isPresent()) {
	        ApiResponse<Users> response = new ApiResponse<>(200, user.get(), "Success");
	        return ResponseEntity.ok(response);
	    }
        ApiResponse<?> response = new ApiResponse<>(404, null, "User not found with id: " + id);
	    // If user is not found, return HTTP 404 (Not Found) with a custom message
	    return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                         .body(response);
	}
	@PostMapping("update_user/{ID}")
	public ResponseEntity<?> update_user(@PathVariable long ID, @RequestBody Users updateUser)
	{
		Optional<Users> existingUser= service.getUserByID(ID);
		if(existingUser.isPresent()) {
			Users user=  existingUser.get();
			user.setEmail(updateUser.getEmail());
	        user.setName(updateUser.getName());
	        
	        // Save the updated user back to the database
	        service.saveuser(user);
			
			ApiResponse<Users> response = new ApiResponse<>(200,user, "User updated successfully");
			return ResponseEntity.ok(response);
		}
		 ApiResponse<String> response = new ApiResponse<>(404, null, "User not found with id: " + ID);
		   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	@PostMapping("/save_user")
	public ResponseEntity<ApiResponse<Users>> saveUser(@RequestBody Users updatedUser) {
	    // Call the service method to save the user
	    ApiResponse<Users> response = service.saveUser(updatedUser);
	    return ResponseEntity.status(response.getStatus()).body(response);
	}

}
