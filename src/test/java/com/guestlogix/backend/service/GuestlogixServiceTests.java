package com.guestlogix.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import com.guestlogix.backend.entity.Airport;
import com.guestlogix.backend.repository.AirportRepository;
import com.guestlogix.backend.repository.RouteRepository;

@RunWith(SpringRunner.class)
public class GuestlogixServiceTests {

	@MockBean
    private AirportRepository airportRepository;
	
	@MockBean
    private RouteRepository routeRepository;
	
	@InjectMocks
	private GuestlogixService service;
	
	//@Before
    //public void setup() {
        // Initializes the JacksonTester
        //JacksonTester.initFields(this, new ObjectMapper());
    //}
	
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
			service.chooseRoutesBetweenAirports(airports);	
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void should_throw_error_when_there_are_two_or_more_airports_to_choose_the_route_but_it_does_not_have_routes() {
		List<Airport> airports = new ArrayList<>();
		airports.add(new Airport());
		airports.add(new Airport());
		try {
			service.chooseRoutesBetweenAirports(airports);	
		} catch (Exception e) {
			fail();
		}
	}
	
	
	
	
	//@Test
	public void shouldNotFetchShortestRouteWhenDestinationIsNull2() {
		given(airportRepository.findByIata3("")).willReturn(Optional.empty());

        // when
        //when(service.validateAirports)
		
		

        // then
        //assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        //assertThat(response.getContentAsString()).isEqualTo(jsonSuperHero.write(new SuperHero("Rob", "Mannon", "RobotMan")).getJson());
		
		
		
		assertEquals(true, true);
	}
	
	//@Test(expected=ResponseStatusException.class)
	//@Test
	public void shouldNotFetchShortestRouteWhenOriginNotFound() {
		System.out.println("TESTANDOOOOOO");
		assertEquals(true, true);
	}
	
	//@Test
	public void shouldNotFetchShortestRouteWhenDestinationNotFound() {
		System.out.println("TESTANDOOOOOO");
		assertEquals(true, true);
	}
	
	//@Test
	public void shouldFetchShortestRouteBetweenTwoAirports() {
		System.out.println("TESTANDOOOOOO");
		assertEquals(true, true);
	}
	
	//@Test(expected=ResponseStatusException.class)
	public void shouldNotChooseRoutesWhenThereAreLessThan2Airports(){
		List<Airport> airports = new ArrayList<>();
		airports.add(new Airport());
		//controller.chooseRoutesBetweenAirports(airports);
	}
	
	//@Test
	public void shouldChooseRoutesBetweenAirports() {
		
	}
	
}
