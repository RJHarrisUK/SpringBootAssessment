package com.poke.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConnectorREST {

	private RestTemplate restTemplate;

	@Autowired
	public ConnectorREST(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	// CREATE
	@GetMapping("/getPokemon/{NameOrId}")
	public ResponseEntity<Object> getPokemon(@PathVariable("NameOrId") String NameOrId) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		return restTemplate.exchange("https://pokeapi.co/api/v2/pokemon/" + NameOrId, HttpMethod.GET, entity,
				Object.class);
	}

}
