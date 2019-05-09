package com.guestlogix.backend.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "AIRPORT")
public class Airport implements Serializable, Comparable<Airport> {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 3)
	@Column(name = "IATA_3")
	private String iata3;	
	
	@NotEmpty
	@Size(max = 80)
	private String name;
	
	@NotEmpty
	@Size(max = 50)
	private String city;

	@NotEmpty
	@Size(max = 50)
	private String country;
	
	@NotEmpty
	private Double latitude;
	
	@NotEmpty
	private Double longitude;
	
	@JsonIgnore
	@Transient
	private boolean visited = false;
	
	@JsonIgnore
	@Transient
	private Integer distance = Integer.MAX_VALUE;
	
	@JsonIgnore
	@Transient
	private Airport previousAirport;
	
	@JsonIgnore
	@Transient
	private List<Route> routes = new ArrayList<>();

	@Override
	public int compareTo(Airport other) {
		return this.getDistance().compareTo(other.getDistance());     
	}

	public void visit() {
		this.visited = true;	
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airport other = (Airport) obj;
		if (iata3 == null) {
			if (other.iata3 != null)
				return false;
		} else if (!iata3.equals(other.iata3))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iata3 == null) ? 0 : iata3.hashCode());
		return result;
	}
	
}
