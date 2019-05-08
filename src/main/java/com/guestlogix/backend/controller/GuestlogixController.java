package com.guestlogix.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.guestlogix.backend.entity.Airport;
import com.guestlogix.backend.entity.Route;
import com.guestlogix.backend.repository.AirportRepository;
import com.guestlogix.backend.repository.RouteRepository;
import com.guestlogix.backend.service.DijkstraService;

@CrossOrigin
@RestController
@RequestMapping("/guestlogix")
public class GuestlogixController {

	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private AirportRepository airportRepository;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/route/{originIata3}/{destinationIata3}")
	public List<Route> fetchShortestRoute(@PathVariable String originIata3, @PathVariable String destinationIata3) {
		Optional<Airport> origin = airportRepository.findByIata3(originIata3);
		if(!origin.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Origin airport not found");
		}
		Optional<Airport> destination = airportRepository.findByIata3(destinationIata3);	
		if(!destination.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Destination airport not found");
		}
		List<Airport> airports = airportRepository.findAll();	
		this.populateRoutes(airports);
		List<Airport> airportsOfRoute = new DijkstraService(airports, origin.get(), destination.get()).fetchShortestPath();	
		return chooseRoutesBetweenAirports(airportsOfRoute);
	}
	
	private void populateRoutes(List<Airport> airports) {
		for (Airport airport : airports) {
			List<Route> possibleRoutes = routeRepository.findByOriginIata3(airport.getIata3());
			airport.setRoutes(possibleRoutes);
		}
	}

	protected List<Route> chooseRoutesBetweenAirports(List<Airport> airports) {
		if(airports.size() < 2) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no route between these airports");
		} else {
			List<Route> bestRoute = new ArrayList<Route>();
			for (int i = 0; i < airports.size() - 1; i++) {
				String originIata3 = airports.get(i).getIata3();
				String destinationIata3 = airports.get(i + 1).getIata3();
				List<Route> connections = routeRepository.findByOriginIata3AndDestinationIata3(originIata3, destinationIata3);
				if(connections.isEmpty()) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There was an internal error of data");
				} else {
					bestRoute.add(connections.stream().findAny().get());
				}
			}
			return bestRoute;
		}
	}
	
}
