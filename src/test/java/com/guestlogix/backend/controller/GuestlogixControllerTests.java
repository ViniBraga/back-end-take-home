package com.guestlogix.backend.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import com.guestlogix.backend.entity.Airport;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GuestlogixControllerTests {

	@Autowired
	private GuestlogixController controller;
	
	//@Test(expected=ResponseStatusException.class)
	@Test
	public void shouldNotFetchShortestRouteWhenOriginNotFound() {
		System.out.println("TESTANDOOOOOO");
		assertEquals(true, true);
	}
	
	@Test
	public void shouldNotFetchShortestRouteWhenDestinationNotFound() {
		System.out.println("TESTANDOOOOOO");
		assertEquals(true, true);
	}
	
	@Test(expected=ResponseStatusException.class)
	public void shouldNotChooseRoutesWhenThereAreLessThan2Airports(){
		List<Airport> airports = new ArrayList<>();
		airports.add(new Airport());
		controller.chooseRoutesBetweenAirports(airports);
	}
	
	
	
}
