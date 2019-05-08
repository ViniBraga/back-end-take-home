package com.guestlogix.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;

@Data
@Entity
@Table(name = "ROUTE")
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(max = 2)
	@Column(name="AIRLINE_DIGIT_CODE_2", nullable=false)
	private String airlineCode;
	
	@NotEmpty
	@JoinColumn(name="AIRLINE_DIGIT_CODE_2", referencedColumnName = "DIGIT_CODE_2", insertable=false, updatable=false)
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne
	private Airline airline;
	
	@NotEmpty
	@Size(max = 3)
	@Column(name="ORIGIN_IATA_3", nullable=false)
	private String originIata3;
	
	@NotEmpty
	@JoinColumn(name="ORIGIN_IATA_3", referencedColumnName = "IATA_3", insertable=false, updatable=false)
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne
	private Airport originAirport;
	
	@NotEmpty
	@Size(max = 3)
	@Column(name="DESTINATION_IATA_3", nullable=false)
	private String destinationIata3;
	
	@NotEmpty
	@JoinColumn(name = "DESTINATION_IATA_3", referencedColumnName = "IATA_3", insertable=false, updatable=false)
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne(fetch = FetchType.LAZY)
	private Airport destinationAirport;	
	
}
