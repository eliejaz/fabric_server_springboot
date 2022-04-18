package com.medicalRecord.hyperLedgerServer.Entity;

import lombok.Data;

@Data
public class Diagnosis {

	String ID ;
	String description ;
	String illness ;
	Prescription []prescriptions ;
}
