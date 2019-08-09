package com.poke.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.poke.persistence.domain.User;

@RestController
public class UserRest {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	public UserRest(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public UserRest() {
	}

	// GO TO USER
	// CREATE
	@PostMapping("/addUser")
	public ResponseEntity<Object> addUser(@RequestBody User user) {

		HttpEntity<User> entity = new HttpEntity<User>(user);

		ResponseEntity<Object> returnMessage = restTemplate.exchange("http://localhost:8077/addUser", HttpMethod.POST,
				entity, Object.class);

		return new ResponseEntity<>(returnMessage, HttpStatus.OK);
	}

	// READ
	@GetMapping("/getUsers")
	public ResponseEntity<Object> getUsers() {

		ResponseEntity<Object> returnMessage = restTemplate.exchange("http://localhost:8077/getUsers", HttpMethod.GET,
				null, Object.class);

		return new ResponseEntity<>(returnMessage, HttpStatus.OK);
	}

	// GO TO API
	// READ
//    @GetMapping("/{id}/pokemon/{pokemon}")
	@GetMapping("getPokemon/{memberNumber}/{nameOrId}")
	public ResponseEntity<Object> findPokemon(@PathVariable Long id, @PathVariable String pokemon) {
		Boolean userCheck = new ResponseEntity<>(userService.checkUser(id), HttpStatus.OK).getBody();
		if (userCheck) {
			Date timeStamp = new Date();
			timeStamp.toLocaleString();
			searchService.sendToQueue(id, pokemon, timeStamp);
			return new ResponseEntity<>(searchService.findPokemon(pokemon), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("user does not exist", HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping("getPokemon/{memberNumber}/{nameOrId}")
	public ResponseEntity<Object> getPokeByName(@PathVariable("memberNumber") Long memberNumber,
			@PathVariable("nameOrId") String nameOrId) {
		Boolean check = (Boolean) restTemplate.getForObject("http://localhost:8077/checkUser/{memberNumber}",
				Object.class, memberNumber);

		if (check == false) {
			return new ResponseEntity<>(check, HttpStatus.NO_CONTENT);
		} else {
		}

		Object pokemon = restTemplate.getForObject("http://localhost:8078/getPokemon/{nameOrId}", Object.class,
				nameOrId);
		return new ResponseEntity<>(pokemon, HttpStatus.OK);
	}
}
