package com.guestlogix.backend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.guestlogix.backend.entity.Airport;
import com.guestlogix.backend.entity.Route;

public class DijkstraService {

	private List<Airport> airports;
	private Airport origin;
	private Airport destination;
	private List<Airport> airportsOfShortestPath = new ArrayList<>();
	
	public DijkstraService(List<Airport> airports, Airport origin, Airport destination) {
		this.airports = airports;
		this.origin = origin;
		this.destination = destination;
	}

	public List<Airport> fetchShortestPath() {
		List<Airport> unvisitedAirports = new ArrayList<>();
		airportsOfShortestPath.add(origin);
		airports.stream().filter(a -> a.getIata3()!= null && a.getIata3().equals(origin.getIata3()))
			.collect(Collectors.toList())
			.stream()
			.findAny()
			.get()
			.setDistance(0);
		
		airports.stream().forEach(a -> unvisitedAirports.add(a));
		Collections.sort(unvisitedAirports);
		this.visitAllAirports(unvisitedAirports);
		Collections.reverse(airportsOfShortestPath);
		return airportsOfShortestPath;
	}

	private void visitAllAirports(List<Airport> unvisitedAirports) {		
		while (!unvisitedAirports.isEmpty()) {
			Airport currentAirport = unvisitedAirports.get(0);
			this.calculateDistanceOfConnectedAirports(currentAirport);
			currentAirport.visit();
			unvisitedAirports.remove(currentAirport);
			Collections.sort(unvisitedAirports);	
		}
	}

	private void calculateDistanceOfConnectedAirports(Airport currentAirport) {
		for (Route currentRoute : currentAirport.getRoutes()) {
			Airport connectedAirport = currentRoute.getDestinationAirport();
			if (connectedAirport != null && !connectedAirport.isVisited()) {
				this.compareDistanceBetween(currentAirport, connectedAirport);
			}	
		}
	}

	private void compareDistanceBetween(Airport currentAirport, Airport connectedAirport) {
		if (connectedAirport.getDistance() > (currentAirport.getDistance())) {
			connectedAirport.setDistance(currentAirport.getDistance());
			connectedAirport.setPreviousAirport(currentAirport);
			this.incrementAirportsOfPath(connectedAirport);	
		}
	}

	private void incrementAirportsOfPath(Airport connectedAirport) {
		if (connectedAirport.equals(destination)) {
			airportsOfShortestPath.clear();
			Airport airportOnPath = connectedAirport;
			airportsOfShortestPath.add(connectedAirport);
			while (airportOnPath.getPreviousAirport() != null) {
				airportsOfShortestPath.add(airportOnPath.getPreviousAirport());
				airportOnPath = airportOnPath.getPreviousAirport();
			}
			Collections.sort(airportsOfShortestPath);
		}
	}

	
}
