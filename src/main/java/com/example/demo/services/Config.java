package com.example.demo.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
public class Config {
	
	private String princessLeiaPeopleId;
	private String darthVaderPeopleId;
	private String aldeaanPlanetId;
	private String deathStarShipId;
	public Config() {
		
	}
	public Config(String princessLeiaPeopleId, String darthVaderPeopleId, String aldeaanPlanetId,
			String deathStarShipId) {
		super();
		this.princessLeiaPeopleId = princessLeiaPeopleId;
		this.darthVaderPeopleId = darthVaderPeopleId;
		this.aldeaanPlanetId = aldeaanPlanetId;
		this.deathStarShipId = deathStarShipId;
	}

	public String getPrincessLeiaPeopleId() {
		return princessLeiaPeopleId;
	}
	public void setPrincessLeiaPeopleId(String princessLeiaPeopleId) {
		this.princessLeiaPeopleId = princessLeiaPeopleId;
	}
	public String getDarthVaderPeopleId() {
		return darthVaderPeopleId;
	}
	public void setDarthVaderPeopleId(String darthVaderPeopleId) {
		this.darthVaderPeopleId = darthVaderPeopleId;
	}
	public String getAldeaanPlanetId() {
		return aldeaanPlanetId;
	}
	public void setAldeaanPlanetId(String aldeaanPlanetId) {
		this.aldeaanPlanetId = aldeaanPlanetId;
	}
	public String getDeathStarShipId() {
		return deathStarShipId;
	}
	public void setDeathStarShipId(String deathStarShipId) {
		this.deathStarShipId = deathStarShipId;
	}
	
	
	

}
