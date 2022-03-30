package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.services.StarWarAPI;

@SpringBootTest
class StarWarApplicationTests {
	
	
	@Test
	void getPeopleDataNameTest() { 
		StarWarAPI swa = new StarWarAPI("https://swapi.dev/api/","people/","starships/","planets/");
		Map<String, Object> actualResponse = (Map<String, Object>) swa.getPeopleData("3");
		assertThat(actualResponse.get("name")).isEqualTo("R2-D2");
	}
	
	@Test
	void getPeopleDataURLTest() { 
		StarWarAPI swa = new StarWarAPI("https://swapi.dev/api/","people/","starships/","planets/");
		Map<String, Object> actualResponse = (Map<String, Object>) swa.getPeopleData("3");
		assertThat(actualResponse.get("url")).isEqualTo( "https://swapi.dev/api/people/3/");
	}
	
	@Test
	void getPlanetDataForId3() { 
		StarWarAPI swa = new StarWarAPI("https://swapi.dev/api/","people/","starships/","planets/");
		Map<String, Object> actualResponse = (Map<String, Object>) swa.getPlanetData("3");
		assertThat(((ArrayList<String>) actualResponse.get("residents")).size()).isEqualTo(0);
	}
	
	@Test
	void getPlanetDataForId1() { 
		StarWarAPI swa = new StarWarAPI("https://swapi.dev/api/","people/","starships/","planets/");
		Map<String, Object> actualResponse = (Map<String, Object>) swa.getPlanetData("1");
		assertThat(((ArrayList<String>) actualResponse.get("residents")).size()).isEqualTo(10);
	}
	
	@Test
	void getStarshipDataForId10() { 
		StarWarAPI swa = new StarWarAPI("https://swapi.dev/api/","people/","starships/","planets/");
		Map<String, Object> actualResponse = (Map<String, Object>) swa.getShipData("10");
		assertThat(actualResponse.get("name")).isEqualTo("Millennium Falcon");
		assertThat(actualResponse.get("model")).isEqualTo("YT-1300 light freighter");
		assertThat(actualResponse.get("starship_class")).isEqualTo("Light freighter");
	}
	
	@Test
	void getInformation() throws ParseException { 
		StarWarAPI swa = new StarWarAPI("https://swapi.dev/api/","people/","starships/","planets/");
		Map<String, Object> actualResponse = (Map<String, Object>) swa.getInformation();
		assertThat(actualResponse.get("isLeiaOnPlanet")).isEqualTo(true);
		assertThat(actualResponse.get("crew")).isEqualTo((long)(342953));
		Map<String, Object> starShip = (Map<String, Object>) actualResponse.get("starship");
		assertThat(starShip.get("name")).isEqualTo("TIE Advanced x1");
		assertThat(starShip.get("model")).isEqualTo("Twin Ion Engine Advanced x1");
		assertThat(starShip.get("class")).isEqualTo("Starfighter");
		
	}
	

}
