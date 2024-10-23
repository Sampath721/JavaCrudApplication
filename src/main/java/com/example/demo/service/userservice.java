package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.userrepository;
import com.example.demo.dto.ApiResponse;
import com.example.demo.model.Users;

@Service
public class userservice {
	@Autowired
	private userrepository userrepo;
	
	 public List<Users> getAllUsers() {
	        return userrepo.findAll();
	 }

	public Optional<Users> getUserByID(long id) {
		return userrepo.findById((long) id);
	}

	public void saveuser(Users User) {
		 userrepo.save(User);
	}
	 public ApiResponse<Users> saveUser(Users updatedUser) {
	        // Create a new Users object
	        Users user = new Users();
	        user.setEmail(updatedUser.getEmail());
	        user.setName(updatedUser.getName());

	        // Save the new user to the database
	        userrepo.save(user);

	        return new ApiResponse<>(201, user, "User created successfully");
	    }
	
}
