package com.guestlogix.backend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "AIRLINE")
public class Airline implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(max = 2)
	@Column(name = "DIGIT_CODE_2")
	private String digitCode2;

	@NotEmpty
	@Size(max = 3)
	@Column(name = "DIGIT_CODE_3")
	private String digitCode3;
	
	@NotEmpty
	@Size(max = 25)
	private String name;
	
	@NotEmpty
	@Size(max = 100)
	private String country;	
	
}
