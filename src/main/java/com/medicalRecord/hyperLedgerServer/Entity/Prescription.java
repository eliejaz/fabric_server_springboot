package com.medicalRecord.hyperLedgerServer.Entity;

import lombok.Data;

@Data
public class Prescription {
	private String id;
	private String medicationName ;
	private String quantity ;
}
