package com.medicalRecord.hyperLedgerServer.Entity;

import lombok.Data;

@Data
public class Patient {

	String ID;
	String firstName ;
	String lastName ;
	String email ;
	String description ;
	String groupType ;
	String []allergies;
	String emergency_contact ;
	Diagnosis diagnosis ;
	String [] doctors ;
}
