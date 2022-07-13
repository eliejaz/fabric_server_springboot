package com.medicalRecord.hyperLedgerServer.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Patient {

	private String id;
	private String firstName ;
	private String last_name ;
	private String email ;
	private String description ;
	@Schema(description = "Blood Type", allowableValues  = {"A+","B+","A-","B-","AB+","AB-","O+","O-"}, required = true) 
	private String groupType ;
	private Set<String>allergies= new HashSet<>() ;
	private String emergencyContact ;
	private List<Diagnosis>diagnosis =new ArrayList<>() ;
	@Schema(description = "If kept empty will automatically add creator doctor.", required = true) 
	private Set<String>doctorsId = new HashSet<>() ;
	
	private String inpatient;
}
