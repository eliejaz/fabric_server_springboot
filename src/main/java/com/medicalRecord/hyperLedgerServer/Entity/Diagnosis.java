package com.medicalRecord.hyperLedgerServer.Entity;

import lombok.Data;

@Data
public class Diagnosis {
	private String id;
	private String description;
	private String illness;
	private Prescription[] prescriptions;
    
    //add doctor
}