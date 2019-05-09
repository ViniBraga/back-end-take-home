package com.guestlogix.backend.service;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import com.guestlogix.backend.entity.Airport;
import com.guestlogix.backend.entity.Route;
import com.guestlogix.backend.repository.AirportRepository;
import com.guestlogix.backend.repository.RouteRepository;



import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringRunner.class)
public class GuestlogixServiceTests {

	@Mock
    private AirportRepository airportRepository;
	
	@Mock
    private RouteRepository routeRepository;
	
	@InjectMocks
	private GuestlogixService service;
	
	@Test(expected = ResponseStatusException.class)
	public void should_return_error_when_origin_is_null() throws Exception {
		Optional<Airport> origin = Optional.empty();
		Optional<Airport> destination = Optional.of(new Airport());
		service.validateAirports(origin, destination);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void should_throw_error_when_destination_is_null() throws Exception {
		Optional<Airport> origin = Optional.of(new Airport());
		Optional<Airport> destination = Optional.empty();
		service.validateAirports(origin, destination);
	}
	
	@Test
	public void should_not_throw_error_when_origin_and_destination_are_not_null() {
		Optional<Airport> origin = Optional.of(new Airport());
		Optional<Airport> destination = Optional.of(new Airport());
		try {
			service.validateAirports(origin, destination);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void populate_airport_routes_when_airport_is_empty() {
		List<Airport> airports = new ArrayList<>();
		try {
			service.populateRoutes(airports);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void populate_airport_routes_when_airport_is_null() {
		List<Airport> airports = null;
		try {
			service.populateRoutes(airports);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test(expected = ResponseStatusException.class)
	public void should_throw_error_when_there_is_null_airports_to_choose_the_route() {
		List<Airport> airports = null;
		service.chooseRoutesBetweenAirports(airports);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void should_throw_error_when_there_are_less_than_two_airports_to_choose_the_route() {
		List<Airport> airports = new ArrayList<>();
		service.chooseRoutesBetweenAirports(airports);
	}
	
	@Test
	public void should_not_throw_error_when_there_are_two_or_more_airports_to_choose_the_route_and_it_has_routes() {
		List<Airport> airports = new ArrayList<>();
		airports.add(new Airport());
		airports.add(new Airport());
		try {
			List<Route> routes = new ArrayList<Route>();
			routes.add(new Route());
			routes.add(new Route());
			when(routeRepository.findByOriginIata3AndDestinationIata3(anyString(), anyString())).thenReturn(routes);
			service.chooseRoutesBetweenAirports(airports);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test(expected = ResponseStatusException.class)
	public void should_throw_error_when_there_are_two_or_more_airports_to_choose_the_route_but_no_route_is_found_for_each() {
		when(routeRepository.findByOriginIata3AndDestinationIata3(anyString(), anyString())).thenReturn(new ArrayList<Route>());
		service.fetchRoute(anyString(), anyString());	
	}
	
}
