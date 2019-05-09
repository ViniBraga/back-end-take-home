package com.guestlogix.backend.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guestlogix.backend.entity.Airport;
import com.guestlogix.backend.entity.Route;

import io.micrometer.core.instrument.util.IOUtils;


public class DijkstraServiceTests {

	private ObjectMapper mapper;
	private List<Airport> airports;
	
	@Before
	public void loadService() throws JsonParseException, IOException {
		
		airports = new ArrayList<Airport>();
	
		mapper = new ObjectMapper();
		
		InputStream abjJson = getClass().getClassLoader().getResourceAsStream("json/abj.json");
		InputStream ouaJson = getClass().getClassLoader().getResourceAsStream("json/oua.json");
		InputStream bruJson = getClass().getClassLoader().getResourceAsStream("json/bru.json");
		InputStream istJson = getClass().getClassLoader().getResourceAsStream("json/ist.json");
		InputStream lfwJson = getClass().getClassLoader().getResourceAsStream("json/lfw.json");
		InputStream bjmJson = getClass().getClassLoader().getResourceAsStream("json/bjm.json");
	
		InputStream routeAbjJson = getClass().getClassLoader().getResourceAsStream("json/routeAbj.json");
		InputStream routeOuaJson = getClass().getClassLoader().getResourceAsStream("json/routeOua.json");
		InputStream routeBruJson = getClass().getClassLoader().getResourceAsStream("json/routeBru.json");
		InputStream routeIstJson = getClass().getClassLoader().getResourceAsStream("json/routeIst.json");
		
		Airport abj = mapper.readValue(IOUtils.toString(abjJson, StandardCharsets.UTF_8), Airport.class);
		Airport oua = mapper.readValue(IOUtils.toString(ouaJson, StandardCharsets.UTF_8), Airport.class);
		Airport bru = mapper.readValue(IOUtils.toString(bruJson, StandardCharsets.UTF_8), Airport.class);
		Airport ist = mapper.readValue(IOUtils.toString(istJson, StandardCharsets.UTF_8), Airport.class);
		Airport lfw = mapper.readValue(IOUtils.toString(lfwJson, StandardCharsets.UTF_8), Airport.class);
		Airport bjm = mapper.readValue(IOUtils.toString(bjmJson, StandardCharsets.UTF_8), Airport.class);

		Route[] routeAbjArray = mapper.readValue(IOUtils.toString(routeAbjJson, StandardCharsets.UTF_8), Route[].class);
		List<Route> routeAbj = convertRouteArrayToList(routeAbjArray);
		abj.setRoutes(routeAbj);
    
		Route[] routeOuaArray = mapper.readValue(IOUtils.toString(routeOuaJson, StandardCharsets.UTF_8), Route[].class);
		List<Route> routeOua = convertRouteArrayToList(routeOuaArray);
		oua.setRoutes(routeOua);
		
		Route[] routeBruArray = mapper.readValue(IOUtils.toString(routeBruJson, StandardCharsets.UTF_8), Route[].class);
		List<Route> routeBru = convertRouteArrayToList(routeBruArray);
		bru.setRoutes(routeBru);
		
		Route[] routeIstArray = mapper.readValue(IOUtils.toString(routeIstJson, StandardCharsets.UTF_8), Route[].class);
		List<Route> routeIst = convertRouteArrayToList(routeIstArray);
		ist.setRoutes(routeIst);
		
		airports.add(abj);
		airports.add(oua);
		airports.add(bru);
		airports.add(ist);
		airports.add(lfw);
		airports.add(bjm);
		
	}
	
	@Test
	public void between_abj_and_bru_are_at_leats_two_airports() throws JsonParseException, JsonMappingException, IOException {
		InputStream abjJson = getClass().getClassLoader().getResourceAsStream("json/abj.json");
		Airport origin = mapper.readValue(IOUtils.toString(abjJson, StandardCharsets.UTF_8), Airport.class);
		InputStream bruJson = getClass().getClassLoader().getResourceAsStream("json/bru.json");
		Airport destination = mapper.readValue(IOUtils.toString(bruJson, StandardCharsets.UTF_8), Airport.class);
		
		List<Airport> list = new DijkstraService(airports, origin, destination).fetchShortestPath();
		assertEquals(2, list.size());
	}
	
	private List<Route> convertRouteArrayToList(Route[] routeArray) {
		List<Route> route = new ArrayList<Route>();
		for (int i = 0; i < routeArray.length; i++) {
			route.add(routeArray[i]);
		}
		return route;
	}
	
}
