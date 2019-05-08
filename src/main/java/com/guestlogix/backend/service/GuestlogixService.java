package com.guestlogix.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.guestlogix.backend.entity.Airport;
import com.guestlogix.backend.entity.Route;
import com.guestlogix.backend.repository.AirportRepository;
import com.guestlogix.backend.repository.RouteRepository;

@Service
public class GuestlogixService {

	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private AirportRepository airportRepository;
	
	public List<Route> fetchShortestRoute(String originIata3, String destinationIata3) {
		Optional<Airport> origin = airportRepository.findByIata3(originIata3);
		Optional<Airport> destination = airportRepository.findByIata3(destinationIata3);	
		this.validateAirports(origin, destination);
		List<Airport> airports = airportRepository.findAll();	
		this.populateRoutes(airports);
		List<Airport> airportsOfRoute = new DijkstraService(airports, origin.get(), destination.get()).fetchShortestPath();	
		return chooseRoutesBetweenAirports(airportsOfRoute);
	}
	
	protected void validateAirports(Optional<Airport> origin, Optional<Airport> destination) {
		if(!origin.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Origin airport not found");
		}
		if(!destination.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Destination airport not found");
		}
	}
	
	protected void populateRoutes(List<Airport> airports) {
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
