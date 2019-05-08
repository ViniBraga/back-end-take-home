package com.guestlogix.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.guestlogix.backend.entity.Route;
import com.guestlogix.backend.service.GuestlogixService;

@CrossOrigin
@RestController
@RequestMapping("/guestlogix")
public class GuestlogixController {

	@Autowired
	private GuestlogixService service;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/route/{originIata3}/{destinationIata3}")
	public List<Route> fetchShortestRoute(@PathVariable String originIata3, @PathVariable String destinationIata3) {
		return service.fetchShortestRoute(originIata3, destinationIata3);
	}
	
}
