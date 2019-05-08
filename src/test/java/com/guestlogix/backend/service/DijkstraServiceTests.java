package com.guestlogix.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.guestlogix.backend.entity.Airport;


public class DijkstraServiceTests {

	private DijkstraService service;
	private Airport destiny;
	private Airport origin;
	private List<Airport> list;
	
	@Before
	public void loadService() {
		destiny = new Airport();
		destiny.setIata3("");
		origin = new Airport();
		origin.setIata3("");
//		destiny.setRoutes(routes);
		
		list = new ArrayList<Airport>();
		list.add(destiny);
		list.add(origin);
		
		service = new DijkstraService(list, origin, destiny);
	}
	
	@Test
	public void fetchShortestPath() {
		
		this.service.fetchShortestPath();
	}
	
}
