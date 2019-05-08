package com.guestlogix.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guestlogix.backend.entity.Route;

public interface RouteRepository  extends JpaRepository<Route, Long>{
	
	List<Route> findByOriginIata3(String originIata3);
	
	List<Route> findByOriginIata3AndDestinationIata3(String originIata3, String destinationIata3);
	
}
