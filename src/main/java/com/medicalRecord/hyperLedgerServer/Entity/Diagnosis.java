package com.medicalRecord.hyperLedgerServer.Entity;

import lombok.Data;

@Data
public class Diagnosis {
    String id;
    String description;
    String illness;
    Prescription[] prescriptions;
}
