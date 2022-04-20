package com.medicalRecord.hyperLedgerServer.Entity;

import lombok.Data;

@Data
public class Patient {

	String ID;
	String firstName ;
	String last_name ;
	String email ;
	String description ;
	String groupType ;
	String []allergies;
	String emergencyContact ;
	Diagnosis[] diagnosis ;
	String [] doctorsId ;
}
