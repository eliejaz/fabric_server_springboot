package com.medicalRecord.hyperLedgerServer.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Patient {

	String id;
	String firstName ;
	String last_name ;
	String email ;
	String description ;
	String groupType ;
	String []allergies;
	String emergencyContact ;
	Diagnosis []diagnosis ;
	@JsonProperty("doctorsId")
	String []doctorsId ;
}
