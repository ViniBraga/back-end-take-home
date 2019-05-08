package com.guestlogix.backend.service;

import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class DijkstraServiceTests {

	private DijkstraService service;
	
	@Before
	public void loadService() {
		service = new DijkstraService(null, null, null);
	}
	
}
