package com.example.demo.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.StarWarAPI;

@RestController
public class ControllerClass {
	
	@Autowired
	private StarWarAPI swa;
	
	@GetMapping("/")
	public String root() {
		return "Welcome to Star Wars!";
	}
	
	@GetMapping("/information/{shipId}/{planetId}")
	public Object getInformation(@PathVariable String shipId, @PathVariable String planetId ) throws ParseException {
		return this.swa.getFormattedData(shipId, planetId);
	}
	

}
