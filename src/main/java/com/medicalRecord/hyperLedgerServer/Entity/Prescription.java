package com.medicalRecord.hyperLedgerServer.Entity;

import lombok.Data;

@Data
public class Prescription {

	String iD ;
	String medication_name ;
	Double quantity ;
}
