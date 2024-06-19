package com.medicalRecord.hyperLedgerServer.Entity;

import lombok.Data;

@Data
public class Prescription {
	String id;
	String medicationName ;
	String quantity ;
}
