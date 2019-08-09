package com.poke.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poke.persistence.domain.User;
import com.poke.service.UserService;

@RestController
public class UserRest {

	public UserService service;

//	@Autowired
//	private RestTemplate restTemplate;

	@Autowired
	public UserRest(UserService service) {
		this.service = service;
	}

	public UserRest() {
	}

	// CREATE
	@PostMapping("/createUser")
	public User createUser(@RequestBody User user) {
		return service.addUser(user);
	}

	// READ
	@GetMapping("/getUsers")
	public List<User> getUsers() {
		return service.getUsers();
	}

	// UPDATE (NOT REQUIRED)

	// DELETE (NOT REQUIRED)

}
