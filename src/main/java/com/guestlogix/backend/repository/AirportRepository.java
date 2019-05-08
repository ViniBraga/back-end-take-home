package com.guestlogix.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guestlogix.backend.entity.Airport;

public interface AirportRepository  extends JpaRepository<Airport, Long>{

	Optional<Airport> findByIata3(String iata3);
	
}
