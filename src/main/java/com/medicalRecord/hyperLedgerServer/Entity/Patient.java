package com.medicalRecord.hyperLedgerServer.Entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Patient {

	@JsonProperty(access = Access.READ_ONLY)
	private String id;
	private String firstName ;
	private String last_name ;
	private String email ;
	private String description ;
	@Schema(description = "Blood Type", allowableValues  = {"A+","B+","A-","B-","AB+","AB-","O+","O-"}, required = true) 
	private String groupType ;
	private Set<String>allergies= new HashSet<>() ;
	private String emergencyContact ;
	@JsonProperty(access = Access.READ_ONLY)
	private Diagnosis []diagnosis ;
	@Schema(description = "If kept empty will automatically add creator doctor.", required = true) 
	private Set<String>doctorsId = new HashSet<>() ;
}
