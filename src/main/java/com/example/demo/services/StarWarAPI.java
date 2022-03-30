package com.example.demo.services;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.lang.String;

@Service
public class StarWarAPI {
	//To store base URL
	private String baseUrl;
	private String peopleUrl;
	private String shipUrl;
	private String planetUrl;
	
	private Config config = new Config("5","4","2","9");
	//To store response object that would be sent to client
	private Map<String, Object>  formattedResponse = new HashMap<String, Object>();
	public StarWarAPI() {
		
	}
	//constructor to initialise the variables
	public StarWarAPI(String baseUrl, String peoplePath, String starshipPath, String planetPath) {
		super();
		this.baseUrl=baseUrl;
		this.peopleUrl=this.baseUrl+peoplePath;
		this.shipUrl = this.baseUrl+starshipPath;
		this.planetUrl =  this.baseUrl+planetPath;
		
	}
	// Function to call the api to get people details
	public Object getPeopleData(String peopleId) {
		System.out.println("Calling getPeopleData with data");
		System.out.println(peopleId);
		RestTemplate restTemplate = new RestTemplate();
		Object response = restTemplate.getForObject(this.peopleUrl+peopleId+"/", Object.class);
		Map<String, Object> apiResponseMap = new HashMap<String, Object>();
		apiResponseMap = (Map<String, Object>) response;
		System.out.println("Completed getPeopleData");
		return apiResponseMap;
	}
	// Function to call the api to get ship details
	public Object getShipData(String shipId) {
		System.out.println("Calling getShipData with data");
		System.out.println(shipId);
		RestTemplate restTemplate = new RestTemplate();
		Object response = restTemplate.getForObject(this.shipUrl+shipId +"/", Object.class);
		Map<String, Object> apiResponseMap = new HashMap<String, Object>();
		apiResponseMap = (Map<String, Object>) response;
		System.out.println("Completed getShipData");
		return apiResponseMap;
	}
	// Function to call the api to get planet details
	public Object getPlanetData(String planetId) {
		System.out.println("Calling getPlanetData with data");
		System.out.println(planetId);
		RestTemplate restTemplate = new RestTemplate();
		Object response = restTemplate.getForObject(this.planetUrl+planetId +"/", Object.class);
		Map<String, Object> apiResponseMap = new HashMap<String, Object>();
		apiResponseMap = (Map<String, Object>) response;
		System.out.println("Completed getPlanetData");
		return apiResponseMap;
	}
	// Function to call APIs
	public Object getDataFromAPI(String URL) {
		RestTemplate restTemplate = new RestTemplate();
		Object response = restTemplate.getForObject(URL, Object.class);
		Map<String, Object> apiResponseMap = new HashMap<String, Object>();
		apiResponseMap = (Map<String, Object>) response;
		return apiResponseMap;
	}
	
	private boolean isLeiaOnPlanetAldaan(ArrayList<String> residents, String leia ) {
		boolean flag = false;
		for(int i=0;i< residents.size();i++) {
			
			if(residents.get(i).equals(leia))
				flag = true;
			
		}
		return flag;
		
	}
	
	public Object getInformation() throws ParseException {
		Map<String, Object> leiaPeopleInfo = (Map<String,Object>) this.getPeopleData(config.getPrincessLeiaPeopleId());
		Map<String, Object> darthPeopleInfo = (Map<String,Object>) this.getPeopleData(config.getDarthVaderPeopleId());
		Map<String, Object> aldeaanPlanetInfo = (Map<String,Object>) this.getPlanetData(config.getAldeaanPlanetId());
		Map<String, Object> deathStartShipInfo = (Map<String,Object>) this.getShipData(config.getDeathStarShipId());
		Map<String, Object> formattedResponse = new HashMap<String, Object>();
		Map<String, Object> starShipResponseInfo = new HashMap<String, Object>();
		//Show information of the API Calls
		System.out.println("Leia Information");
		System.out.println(leiaPeopleInfo);
		System.out.println("Darth Information");
		System.out.println(darthPeopleInfo);
		System.out.println("Aldeaan Information");
		System.out.println(aldeaanPlanetInfo);
		System.out.println("Death Star Information");
		System.out.println(deathStartShipInfo);
		//To see if Leila is on planet Aldeaan or not
		boolean isLeiaOnPlanet = this.isLeiaOnPlanetAldaan((ArrayList<String>)(aldeaanPlanetInfo.get("residents")), (String)(leiaPeopleInfo.get("url")));
		
		//Get Darth Starship details
		ArrayList<String> darthShip = (ArrayList<String>) darthPeopleInfo.get("starships");
		Map<String, Object> darthShipDetail = new HashMap<String, Object>();;
		if(darthShip.size()!=0)
			{darthShipDetail=  (Map<String, Object>) this.getDataFromAPI(darthShip.get(0));
			starShipResponseInfo.put("name", darthShipDetail.get("name"));
			starShipResponseInfo.put("model", darthShipDetail.get("model"));
			starShipResponseInfo.put("class", darthShipDetail.get("starship_class"));
			
			}
		
		//Get number of crews
		long crew = (long) NumberFormat.getNumberInstance(java.util.Locale.US).parse((String) deathStartShipInfo.get("crew"));
		
		//Format and send the response
		formattedResponse.put("starship", starShipResponseInfo);
		formattedResponse.put("crew", crew);
		formattedResponse.put("isLeiaOnPlanet", isLeiaOnPlanet);
		return formattedResponse;
			
		
	}
}
	
