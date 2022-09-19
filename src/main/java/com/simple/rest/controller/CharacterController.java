package com.simple.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.javafaker.Faker;
import com.simple.rest.error.CharacterNotFound;

//Recurso
@RequestMapping("/characters")
//Estereotipo
@RestController
public class CharacterController {
	
	private Faker faker = new Faker();
	private List<String> character = new ArrayList();
	
	@PostConstruct
	public void init() {
		for(int i=0; i < 10; i++) {
			character.add(faker.animal().name());
		}
	}
	
	@GetMapping(value= "/animales")
	public List<String> getCharacters() {
		return character;
	}
	
	@GetMapping(value= "/animales/{name}")
	public String getCharacterByName(@PathVariable("name") String name) {
		return character.stream().filter(c -> c.equals(name)).findAny()
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("%s not found",name)));
	}

    //search?prefix=mouse	
	@GetMapping(value= "/animales/search")
	public List<String> getCharactersByPrefix(@RequestParam("prefix") String prefix) {
		List<String> result = character.stream().filter(c -> c.startsWith(prefix)).collect(Collectors.toList());
		if (result.isEmpty()) {
			throw new CharacterNotFound();
		}
			
		return result;
	}
}
