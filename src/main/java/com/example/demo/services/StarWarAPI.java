package com.example.demo.services;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StarWarAPI {
	//To store base URL
	private String baseUrl="https://swapi.dev/api/";
	
	// For storing people related information
	private String peopleId;
	private String peopleUrl;
	
	//For storing ship related information
	private String deathStarShipId;
	private String shipUrl;
	
	//To store planet related information
	private String planetId;
	private String planetUrl;
	//To store response object that would be sent to client
	private Map<String, Object>  formattedResponse = new HashMap<String, Object>();
	
	//constructor to initialise the variables
	public StarWarAPI() {
		super();
		this.peopleId = "5";
		this.peopleUrl = this.baseUrl+"people/";
		
		this.deathStarShipId = "9";
		this.shipUrl = this.baseUrl+"starships/";
		
		this.planetId = "3";
		this.planetUrl = this.baseUrl+"planets/";
		
		
	}
	// Function to call the api to get people details
	public Object getPeopleData() {
		RestTemplate restTemplate = new RestTemplate();
		Object response = restTemplate.getForObject(this.peopleUrl+this.peopleId+"/", Object.class);
		Map<String, Object> apiResponseMap = new HashMap<String, Object>();
		apiResponseMap = (Map<String, Object>) response;
		return apiResponseMap;
	}
	// Function to call the api to get ship details
	public Object getShipData(String shipId) {
		RestTemplate restTemplate = new RestTemplate();
		Object response = restTemplate.getForObject(this.shipUrl+shipId +"/", Object.class);
		Map<String, Object> apiResponseMap = new HashMap<String, Object>();
		apiResponseMap = (Map<String, Object>) response;
		return apiResponseMap;
	}
	
	// To prepare the response
	public Object getFormattedData(String shipId, String planetId) throws ParseException {
		System.out.println(shipId);
		System.out.println(planetId);
		//gets people data 
		Map<String, Object> peopleResponse = (Map<String, Object>) this.getPeopleData();
		Map<String, Object> shipDetails = new HashMap<String, Object>();
		Map<String, Object> shipData = new HashMap<String, Object>();
		//gets ship data
		if(Integer.parseInt(shipId)>8)
		{
			shipData = (Map<String, Object>) this.getShipData(shipId);
			//Store ship related infor
			shipDetails.put("name", shipData.get("name"));
			shipDetails.put("class", shipData.get("starship_class"));
			shipDetails.put("model", shipData.get("model"));
		}
		else {
			shipDetails= null;
		}
		Map<String, Object> deathStarshipData = (Map<String, Object>) this.getShipData(this.deathStarShipId);
//		Map<String, Object> shipData = (Map<String, Object>) this.getShipData(shipId);
		System.out.println(shipData);
		//creates a map to store the ship related infor
		
		
		//To get crew count
		long crew = (long) NumberFormat.getNumberInstance(java.util.Locale.US).parse((String) deathStarshipData.get("crew"));
//		String crew =  (String) deathStarshipData.get("crew");
		// TO check if Leila is on the planet or not
		boolean isLeilaOnPlanet = peopleResponse.get("homeworld").equals(this.planetUrl+planetId+"/");
		//Putting everything into the formattedResponse
		this.formattedResponse.put("crew", crew);
		this.formattedResponse.put("isLeilaOnPlanet", isLeilaOnPlanet);
		this.formattedResponse.put("starship", shipDetails);
		return this.formattedResponse;
			
		
	}
	

}
