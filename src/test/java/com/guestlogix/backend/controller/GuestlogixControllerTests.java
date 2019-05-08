package com.guestlogix.backend.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guestlogix.backend.entity.Route;
import com.guestlogix.backend.service.GuestlogixService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.BDDMockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(GuestlogixController.class)
public class GuestlogixControllerTests {

	@Autowired
    private MockMvc mvc;
 
	@MockBean
    private GuestlogixService service;

	@Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }
	
	@Test
	public void should_try_to_fetch_shortest_route_when_url_is_correct() throws Exception {
		List<Route> routes = new ArrayList<>();
		given(service.fetchShortestRoute("", "")).willReturn(routes);

        MockHttpServletResponse response = mvc.perform(get("/guestlogix/route/AAA/AAA").accept(MediaType.APPLICATION_JSON))
        		.andReturn()
        		.getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	public void shouldNotFindMethodFetchShortestRouteWhenUrlIsIncorrect() throws Exception {
		List<Route> routes = new ArrayList<>();
		given(service.fetchShortestRoute("", "")).willReturn(routes);

        MockHttpServletResponse response = mvc.perform(get("/guestlogix/route/AAA").accept(MediaType.APPLICATION_JSON))
        		.andReturn()
        		.getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
}
